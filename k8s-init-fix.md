### 2.2.1 版本统一

- Docker 18.09.0
- kubeadm-1.14.0-0 
- kubelet-1.14.0-0 
- kubectl-1.14.0-0
  - k8s.gcr.io/kube-apiserver:v1.14.0
  - k8s.gcr.io/kube-controller-manager:v1.14.0
  - k8s.gcr.io/kube-scheduler:v1.14.0
  - k8s.gcr.io/kube-proxy:v1.14.0
  - k8s.gcr.io/pause:3.1
  - k8s.gcr.io/etcd:3.3.10
  - k8s.gcr.io/coredns:1.3.1
- calico:v3.9

## 2.2.2 准备3台centos

```shell
01 `搭建前准备`
mkdir D:\VM\k8s-docker-centos7 【创建目录】
cd D:\VM\k8s-docker-centos7 【切换目录】
type nul>Vagrantfile 【创建Vagrantfile文件】
echo [fileContent]>Vagrantfile 【编辑文件】
# ====================================================================================
boxes = [
	{
		:name => "master-kubeadm-k8s",
		:eth1 => "10.13.11.21",
		:mem => "2048",
		:cpu => "2",
		:sshport => 22230
	},
	{
		:name => "worker01-kubeadm-k8s",
		:eth1 => "10.13.11.22",
		:mem => "2048",
		:cpu => "2",
		:sshport => 22231
	},
	{
		:name => "worker02-kubeadm-k8s",
		:eth1 => "10.13.11.23",
		:mem => "2048",
		:cpu => "2",
		:sshport => 22232
	}
]
Vagrant.configure(2) do |config|
	config.vm.box = "centos/7"
	boxes.each do |opts|
		config.vm.define opts[:name] do |config|
			config.vm.hostname = opts[:name]
			config.vm.network :public_network, ip: opts[:eth1]
			config.vm.network "forwarded_port", guest: 22, host: 2222, id: "ssh", disabled: "true"
		config.vm.network "forwarded_port", guest: 22, host: opts[:sshport]
			config.vm.provider "vmware_fusion" do |v|
				v.vmx["memsize"] = opts[:mem]
				v.vmx["numvcpus"] = opts[:cpu]
			end
			config.vm.provider "virtualbox" do |v|
				v.customize ["modifyvm", :id, "--memory", opts[:mem]]
			v.customize ["modifyvm", :id, "--cpus", opts[:cpu]]
				v.customize ["modifyvm", :id, "--name", opts[:name]]
			end
		end
	end
end
# ====================================================================================
02 `启动之后进入到对应的centos里面，使得root账户能够登陆，从而使用XShell登陆`
	vagrant up [启动虚拟机]
	vagrant ssh master-kubeadm-k8s [进入manager-node]
	vagrant ssh worker01-kubeadm-k8s [进入worker01-node]
	vagrant ssh worker02-kubeadm-k8s [进入worker02-node]
03 `分别登陆三个节点,执行下面操作,改成可以密码登陆root账户`
	sudo -i [进入root账户]
	vi /etc/ssh/sshd_config [设置root账户可以密码登陆]
		修改PasswordAuthentication yes
	passwd [修改密码]
	systemctl restart sshd [重启sshd服务]
```

## 2.2.3 更新并安装依赖

> 3台机器都需要执行

```shell
yum -y update
yum install -y conntrack ipvsadm ipset jq sysstat curl iptables libseccomp
```

## 2.2.4 安装Docker

> 3台机器都需要执行，安装版本为18.09.0

```shell
01 `进入虚拟机`
    vagrant ssh [nodeName]
02 `卸载之前安装的docker`
    sudo yum remove docker docker latest docker-latest-logrotate \
    docker-logrotate docker-engine docker-client docker-client-latest docker-common
03 `安装必要依赖`
    sudo yum install -y yum-utils device-mapper-persistent-data lvm2
04 `添加软件源信息`
    sudo yum-config-manager \
    --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
    yum list | grep docker-ce
05 `更新yum缓存`
    sudo yum makecache fast
06 `安装docker`
    sudo yum install -y docker-ce-18.09.0 docker-ce-cli-18.09.0 containerd.io [指定安装docker版本]
07 `启动docker并设置开机启动`
    sudo systemctl start docker && sudo systemctl enable docker
08 `测试docker安装是否成功`
    sudo docker run hello-world
```

## 2.2.5 修改hosts文件

```shell
01 `master`
# 设置master的hostname，并且修改hosts文件
	sudo hostnamectl set-hostname m
02 `两个worker`
# 设置worker01/02的hostname，并且修改hosts文件
	sudo hostnamectl set-hostname w1
	sudo hostnamectl set-hostname w2
03 `三台机器`
	vi /etc/hosts
# ====================================================================================
10.13.11.21 m
10.13.11.22 w1
10.13.11.23 w2
# ====================================================================================
04 `使用ping测试一下`
	ping m
	ping w1
	ping w2
```

## 2.2.6 系统基础前提配置

```shell
01 `关闭防火墙`
	systemctl stop firewalld && systemctl disable firewalld
02 `关闭selinux`
	setenforce 0
	sed -i 's/^SELINUX=enforcing$/SELINUX=permissive/' /etc/selinux/config
03 `关闭swap`
	swapoff -a
	sed -i '/swap/s/^\(.*\)$/#\1/g' /etc/fstab
04 `配置iptables的ACCEPT规则`
	iptables -F && iptables -X && iptables \
    -F -t nat && iptables -X -t nat && iptables -P FORWARD ACCEPT
05 `设置系统参数`
# ====================================================================================
cat <<EOF >  /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sysctl --system
# =======================================================================================
```

## 2.2.7 Installing kubeadm, kubelet and kubectl

```shell
01 `配置yum源`
# ====================================================================================
cat <<EOF > /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=http://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=0
repo_gpgcheck=0
gpgkey=http://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg
       http://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
EOF
# ====================================================================================
02 `安装kubeadm&kubelet&kubectl`
	yum install -y kubeadm-1.14.0-0 kubelet-1.14.0-0 kubectl-1.14.0-0
03 `docker和k8s设置同一个cgroup`
# docker
	vi /etc/docker/daemon.json 【文件没内容的话，就新建；有的话，就加上这一句，注意文件的格式[逗号]】
# ====================================================================================
{
	"exec-opts": ["native.cgroupdriver=systemd"]
}
# ====================================================================================  
	systemctl restart docker 【`重启docker，一定要执行`】
# kubelet
	sed -i "s/cgroup-driver=systemd/cgroup-driver=cgroupfs/g" /etc/systemd/system/kubelet.service.d/10-kubeadm.conf 【`找不到内容没关系`】
	systemctl enable kubelet && systemctl start kubelet 【`重启kubelet，一定要执行`】
```

## 2.2.8 proxy/pause/scheduler等国内镜像

```shell
01 `查看kubeadm使用的镜像`
	kubeadm config images list
# ====================================================================================
k8s.gcr.io/kube-apiserver:v1.14.0
k8s.gcr.io/kube-controller-manager:v1.14.0
k8s.gcr.io/kube-scheduler:v1.14.0
k8s.gcr.io/kube-proxy:v1.14.0
k8s.gcr.io/pause:3.1
k8s.gcr.io/etcd:3.3.10
k8s.gcr.io/coredns:1.3.1
# ====================================================================================
02 `解决国外镜像不能访问的问题`
# 创建kubeadm.sh脚本，用于拉取镜像/打tag/删除原有镜像
	vi kubeadm.sh
# ====================================================================================
#!/bin/bash
set -e
KUBE_VERSION=v1.14.0
KUBE_PAUSE_VERSION=3.1
ETCD_VERSION=3.3.10
CORE_DNS_VERSION=1.3.1
GCR_URL=k8s.gcr.io
ALIYUN_URL=registry.cn-hangzhou.aliyuncs.com/google_containers
images=(kube-proxy:${KUBE_VERSION}
kube-scheduler:${KUBE_VERSION}
kube-controller-manager:${KUBE_VERSION}
kube-apiserver:${KUBE_VERSION}
pause:${KUBE_PAUSE_VERSION}
etcd:${ETCD_VERSION}
coredns:${CORE_DNS_VERSION})
for imageName in ${images[@]} ; do
	docker pull $ALIYUN_URL/$imageName
	docker tag  $ALIYUN_URL/$imageName $GCR_URL/$imageName
	docker rmi $ALIYUN_URL/$imageName
done
# ====================================================================================
03 `运行脚本和查看镜像`
	sh ./kubeadm.sh 【运行脚本】
	docker images 【查看镜像】
04 `将这些镜像推送到自己的阿里云仓库`【可选，根据自己实际的情况】
	docker login --username=happyeveryday2019 registry.cn-hangzhou.aliyuncs.com 【登录自己的阿里云仓库，master节点执行即可】 
	密码：******
	vi kubeadm-push-aliyun.sh
# ====================================================================================
#!/bin/bash
set -e
KUBE_VERSION=v1.14.0
KUBE_PAUSE_VERSION=3.1
ETCD_VERSION=3.3.10
CORE_DNS_VERSION=1.3.1
GCR_URL=k8s.gcr.io
ALIYUN_URL=registry.cn-hangzhou.aliyuncs.com/841863085
images=(kube-proxy:${KUBE_VERSION}
kube-scheduler:${KUBE_VERSION}
kube-controller-manager:${KUBE_VERSION}
kube-apiserver:${KUBE_VERSION}
pause:${KUBE_PAUSE_VERSION}
etcd:${ETCD_VERSION}
coredns:${CORE_DNS_VERSION})
for imageName in ${images[@]} ; do
	docker tag $GCR_URL/$imageName $ALIYUN_URL/$imageName
	docker push $ALIYUN_URL/$imageName
	docker rmi $ALIYUN_URL/$imageName
done
# ====================================================================================
06 `运行脚本`
	sh ./kubeadm-push-aliyun.sh
```

## 2.2.9 kube init初始化master

> **官网：** https://kubernetes.io/docs/reference/setup-tools/kubeadm/kubeadm/

### 2.2.9.1  初始化master节点

```shell
01 `初始化master节点`
	kubeadm reset 【初始化集群状态】
	kubeadm init --kubernetes-version=1.14.0 \
    --apiserver-advertise-address=10.13.11.21 \
    --pod-network-cidr=10.244.0.0/16 【初始化master节点】
# 注意：记得保存好最后kubeadm join的信息。
# =======================================================================================
Your Kubernetes control-plane has initialized successfully!

To start using your cluster, you need to run the following as a regular user:

  mkdir -p $HOME/.kube
  sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
  sudo chown $(id -u):$(id -g) $HOME/.kube/config

You should now deploy a pod network to the cluster.
Run "kubectl apply -f [podnetwork].yaml" with one of the options listed at:
  https://kubernetes.io/docs/concepts/cluster-administration/addons/

Then you can join any number of worker nodes by running the following on each as root:

kubeadm join 10.13.11.21:6443 --token fag134.3wot9edrvs82vh6d \
    --discovery-token-ca-cert-hash sha256:1df02a06552c02ba0e28e00c80a50e9ff40da81a4cdd53c136a16d3c0233f450
# =======================================================================================
02 `根据日志提示执行`
	mkdir -p $HOME/.kube
	sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
	sudo chown $(id -u):$(id -g) $HOME/.kube/config
03 `查看pod`
	等待一会儿，同时可以发现像etcd，controller，scheduler等组件都以pod的方式安装成功了
# 注意：coredns没有启动，需要安装网络插件
	kubectl get pods -n kube-system 【查看kube-system的pods】
	kubectl get pods --all-namespaces 【查看所有pods】
# =======================================================================================
NAME                        READY   STATUS    RESTARTS   AGE
coredns-fb8b8dccf-f7g6g     0/1     Pending   0          7m30s
coredns-fb8b8dccf-hx765     0/1     Pending   0          7m30s
etcd-m                      1/1     Running   0          6m30s
kube-apiserver-m            1/1     Running   0          6m36s
kube-controller-manager-m   1/1     Running   0          6m42s
kube-proxy-w9m72            1/1     Running   0          7m30s
kube-scheduler-m            1/1     Running   0          6m24s
# =======================================================================================
04 `健康检查`
	curl -k https://localhost:6443/healthz
# =======================================================================================
[root@master-kubeadm-k8s ~]# curl -k https://localhost:6443/healthz
ok
# =======================================================================================
```

### 2.2.9.2 kube init流程 ？？？？？

```shell
01 `进行一系列检查，以确定这台机器可以部署kubernetes`
02 `生成kubernetes对外提供服务所需要的各种证书可对应目录`
	/etc/kubernetes/pki/*
03 `为其他组件生成访问kube-ApiServer所需的配置文件`
	ls /etc/kubernetes/
    admin.conf  controller-manager.conf  kubelet.conf  scheduler.conf
04 `为 Master组件生成Pod配置文件`
    ls /etc/kubernetes/manifests/*.yaml
    kube-apiserver.yaml 
    kube-controller-manager.yaml
    kube-scheduler.yaml
05 `生成etcd的Pod YAML文件`
    ls /etc/kubernetes/manifests/*.yaml
    kube-apiserver.yaml 
    kube-controller-manager.yaml
    kube-scheduler.yaml
	etcd.yaml
06 `一旦这些 YAML文件出现在被 kubelet监视的/etc/kubernetes/manifests/目录下，kubelet就会自动创建这些yaml文件定义的pod，即master组件的容器。master容器启动后，kubeadm会通过检查localhost:443/healthz这个master组件的健康状态检查URL，等待master组件完全运行起来`
07 `为集群生成一个bootstrap token`
08 `将ca.crt等Master节点的重要信息，通过ConfigMap的方式保存在etcd中，工后续部署node节点使用`
09 `最后一步是安装默认插件，kubernetes默认kube-proxy和DNS两个插件是必须安装的`
```

## 2.2.10 部署calico网络插件

```shell
# 选择网络插件
	https://kubernetes.io/docs/concepts/cluster-administration/addons/
# calico网络插件
	https://docs.projectcalico.org/v3.9/getting-started/kubernetes/
# 注意：calico，同样在master节点上操作
01 `可以先手动pull一下` 【可能拉取较慢】
	curl https://docs.projectcalico.org/v3.9/manifests/calico.yaml | grep image 【版本会变化，需要根据实际情况拉取镜像】
# =======================================================================================
	      image: calico/cni:v3.9.3
          image: calico/pod2daemon-flexvol:v3.9.3
          image: calico/node:v3.9.3
          image: calico/kube-controllers:v3.9.3
# =======================================================================================
	docker pull calico/cni:v3.9.3
    docker pull calico/pod2daemon-flexvol:v3.9.3
    docker pull calico/node:v3.9.3
    docker pull calico/kube-controllers:v3.9.3
    `官方镜像拉取太慢，用Jack老师的`
    docker pull registry.cn-hangzhou.aliyuncs.com/itcrazy2016/kube-controllers:v3.9.3
	docker pull registry.cn-hangzhou.aliyuncs.com/itcrazy2016/cni:v3.9.3
	docker pull registry.cn-hangzhou.aliyuncs.com/itcrazy2016/pod2daemon-flexvol:v3.9.3
	docker pull registry.cn-hangzhou.aliyuncs.com/itcrazy2016/node:v3.9.3
	`打tag`
	docker tag registry.cn-hangzhou.aliyuncs.com/itcrazy2016/kube-controllers:v3.9.3 \
    calico/kube-controllers:v3.9.3
	docker tag registry.cn-hangzhou.aliyuncs.com/itcrazy2016/cni:v3.9.3 \
    calico/cni:v3.9.3
	docker tag registry.cn-hangzhou.aliyuncs.com/itcrazy2016/pod2daemon-flexvol:v3.9.3 \
    calico/pod2daemon-flexvol:v3.9.3
	docker tag registry.cn-hangzhou.aliyuncs.com/itcrazy2016/node:v3.9.3 \
    calico/node:v3.9.3
	`删除registry.cn-hangzhou.aliyuncs.com/itcrazy2016/格式的镜像` 
# 注意：打tag不会改变imageId，会删除calico的镜像  
	docker rmi -f $(docker images registry.cn-hangzhou.aliyuncs.com/itcrazy2016/* -aq)
02 `在k8s中安装calico`
	yum install -y wget
	wget https://docs.projectcalico.org/v3.9/manifests/calico.yaml
	kubectl apply -f calico.yaml
03 `确认一下calico是否安装成功`
	kubectl get pods --all-namespaces -w 【实时查看所有的Pods】
```

## 2.2.11 kube join

```shell
01 记得保存初始化master节点的最后打印信息【注意这边大家要自己的，下面我的只是一个参考】
	kubeadm join 10.13.11.21:6443 --token fag134.3wot9edrvs82vh6d \
    --discovery-token-ca-cert-hash sha256:1df02a06552c02ba0e28e00c80a50e9ff40da81a4cdd53c136a16d3c0233f450【worker上面执行】
02 在master节点上检查集群信息
	kubectl get nodes
# =======================================================================================
NAME                   STATUS   ROLES    AGE     VERSION
master-kubeadm-k8s     Ready    master   19m     v1.14.0
worker01-kubeadm-k8s   Ready    <none>   3m6s    v1.14.0
worker02-kubeadm-k8s   Ready    <none>   2m41s   v1.14.0
# =======================================================================================
```

## 2.2.12 再次体验Pod

```shell
01 `定义pod.yml文件，比如pod_nginx_rs.yaml` 【不能使用tab，只能用空格】
	mkdir pod_nginx_rs
	cd pod_nginx_rs
# =======================================================================================
cat > pod_nginx_rs.yaml <<EOF
apiVersion: apps/v1
kind: ReplicaSet
metadata:
  name: nginx
  labels:
    tier: frontend
spec:
  replicas: 3
  selector:
    matchLabels:
      tier: frontend
  template:
    metadata:
      name: nginx
      labels:
        tier: frontend
    spec:
      containers:
      - name: nginx
        image: nginx
        ports:
        - containerPort: 80
EOF
# =======================================================================================
02 `根据pod_nginx_rs.yml文件创建pod`
	kubectl apply -f pod_nginx_rs.yaml
03 `查看pod`
    kubectl get pods
    kubectl get pods -o wide
    kubectl describe pod nginx
04 `感受通过rs将pod扩容`
	kubectl scale rs nginx --replicas=5
	kubectl get pods -o wide
05 `删除pod`
	kubectl delete -f pod_nginx_rs.yaml
```

