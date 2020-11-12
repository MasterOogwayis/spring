# 什么是版本控制系统（VCS）

### 版本控制：最基本功能

## 中央式版本控制系统

![image-20181211215950621](/Users/xuhaoliang/Library/Application Support/typora-user-images/image-20181211215950621.png)



![image-20181211220207826](/Users/xuhaoliang/Library/Application Support/typora-user-images/image-20181211220207826.png)

# git status

# HEAD、master 与 branch

## HEAD：当前 commit 的引用

## branch：对commit的引用

![image-20181211220713784](/Users/xuhaoliang/Library/Application Support/typora-user-images/image-20181211220713784.png)



git commit

![img](https://user-gold-cdn.xitu.io/2017/11/20/15fd779f983c81e7?imageslim)

git log

## master: 默认 branch

```
master` ，其实是一个特殊的 `branch`：它是 Git 的默认 `branch
```

所谓的「默认 branch」，主要有两个特点：

1. 新创建的 repository（仓库）是没有任何 `commit` 的。但在它创建第一个 `commit` 时，会把 `master` 指向它，并把 `HEAD` 指向 `master`。

   ![img](https://user-gold-cdn.xitu.io/2017/11/20/15fd779f5c66ac9e?imageslim)

2. 当有人使用 `git clone` 时，除了从远程仓库把 `.git` 这个仓库目录下载到工作目录中，还会 `checkout` （签出） `master`（`checkout` 的意思就是把某个 `commit` 作为当前 `commit`，把 `HEAD` 移动过去，并把工作目录的文件内容替换成这个 `commit` 所对应的内容）。

![img](https://user-gold-cdn.xitu.io/2017/11/20/15fd779f5c191a3f?imageslim)

## branch 的通俗化理解

`branch` 理解为从初始 `commit` 到 `branch` 所指向的 `commit` 之间的所有 `commit`s 的一个「串」。例如下面这张图：

![img](https://user-gold-cdn.xitu.io/2017/11/20/15fd779fa5e6970d?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

bramch的理解

1.所有的 `branch` 之间都是平等的。

![img](https://user-gold-cdn.xitu.io/2017/11/20/15fd779ff346fbd7?imageslim)

2. branch` 包含了从初始 `commit` 到它的所有路径，而不是一条路径。并且，这些路径之间也是彼此平等的。

![img](https://user-gold-cdn.xitu.io/2017/11/22/15fe3354a1d3cd26?imageslim)

git checkout -b feature1

....

git commit 

![img](https://user-gold-cdn.xitu.io/2017/11/22/15fe3354a2a32692?imageslim)

git checkout master

....

git commit 

![img](https://user-gold-cdn.xitu.io/2017/11/22/15fe3354ab0861a7?imageslim)

### 删除 branch

git branch -d feature1

![img](https://user-gold-cdn.xitu.io/2017/11/29/16006b7e3d35fe54?imageslim)

需要说明的有两点：

1. `HEAD` 指向的 `branch` 不能删除。如果要删除 `HEAD` 指向的 `branch`，需要先用 `checkout` 把 `HEAD` 指向其他地方。

2. 由于 Git 中的 `branch` 只是一个引用，所以删除 `branch` 的操作也只会删掉这个引用，并不会删除任何的 `commit`。（不过如果一个 `commit` 不在任何一个 `branch` 的「路径」上，或者换句话说，如果没有任何一个 `branch` 可以回溯到这条 `commit`（也许可以称为野生 `commit`？），那么在一定时间后，它会被 Git 的回收机制删除掉。）

3. 出于安全考虑，没有被合并到 `master` 过的 `branch` 在删除时会失败（因为怕你误删掉「未完成」的 `branch` 啊）：

   ## 引用」的本质

   所谓「引用」（reference），其实就是一个个的字符串。这个字符串可以是一个 `commit` 的 SHA-1 码（例：`c08de9a`），也可以是一个 `branch`（例：`ref: refs/heads/xxx）。

   Git 中的 `HEAD` 和每一个 `branch` 以及其他的引用，都是以文本文件的形式存储在本地仓库 `.git` 目录中，而 Git 在工作的时候，就是通过这些文本文件的内容来判断这些所谓的「引用」是指向谁的。





# push 的本质

实质上，`push` 做的事是：把当前 `branch` 的位置（即它指向哪个 `commit`）上传到远端仓库，并把它的路径上的 `commit`s 一并上传。

![æ master push å°è¿ç¨ä"åº](https://user-gold-cdn.xitu.io/2017/11/29/1600725e9973f71d?imageslim)

# feature1提交



git checkout feature1
git push origin feature1

![push feature1 å°è¿ç¨ä"åº](https://user-gold-cdn.xitu.io/2017/11/29/160073ccda56ef07?imageslim)



# merge：合并 commits

它做的事也是合并：指定一个 `commit`，把它合并到当前的 `commit` 来

git merge branch1

![img](https://user-gold-cdn.xitu.io/2017/11/21/15fddc2aad5a0279?imageslim)

`merge` 有什么用

1. 合并分支

   当一个 `branch` 的开发已经完成，需要把内容合并回去时，用 `merge` 来进行合并。

2. `pull` 的内部操作

   之前说过，`pull` 的实际操作其实是把远端仓库的内容用 `fetch` 取下来之后，用 `merge` 来合并。



## 冲突

1. 解决掉冲突
2. 手动 `commit` 一下

## HEAD 落后于 目标 commit——fast-forward

git merge feature1

![img](https://user-gold-cdn.xitu.io/2017/11/21/15fddc2b2486758a?imageslim)

![img](https://user-gold-cdn.xitu.io/2017/11/21/15fddc2b46c69d46?imageslim)

# git log

在 Git 中，有两个「偏移符号」： `^` 和 `~`。

`^` 的用法：在 `commit` 的后面加一个或多个 `^` 号，可以把 `commit` 往回偏移，偏移的数量是 `^` 的数量。例如：`master^` 表示 `master` 指向的 `commit` 之前的那个 `commit`； `HEAD^^` 表示 `HEAD` 所指向的 `commit` 往前数两个 `commit`。

```
~` 的用法：在 `commit` 的后面加上 `~` 号和一个数，可以把 `commit` 往回偏移，偏移的数量是 `~` 号后面的数。例如：`HEAD~5` 表示 `HEAD` 指向的 `commit`往前数 5 个 `commit
```

1. 查看历史中的多个commit：log
   1. 查看详细改动： `git log -p`
   2. 查看大致改动：`git log --stat`
2. 查看具体某个commit: show
   1. 要看最新 `commit` ，直接输入 `git show` ；要看指定 `commit` ，输入 `git show commit的引用或SHA-1`
   2. 如果还要指定文件，在 `git show` 的最后加上文件名
3. 查看未提交的内容：diff
   1. 查看暂存区和上一条 `commit` 的区别：`git diff --staged`（或 `--cached`）
   2. 查看工作目录和暂存区的区别：`git diff` 不加选项参数
   3. 查看工作目录和上一条 `commit` 的区别：`git diff HEAD`





# rebase

git merge branch1

![img](https://user-gold-cdn.xitu.io/2017/11/21/15fdea7b6646a1f3?imageslim)

git checkout branch1
git rebase master

![img](https://user-gold-cdn.xitu.io/2017/11/30/1600abd620a8e28c?imageslim)

commit -—amend

![img](https://user-gold-cdn.xitu.io/2017/11/21/15fdf0187f2f4b2d?imageslim)

## reset --hard 丢弃最新的提交

![img](https://user-gold-cdn.xitu.io/2017/11/22/15fe19c8a6101d7f?imageslim)

git reset --hard HEAD^

![img](https://user-gold-cdn.xitu.io/2017/11/22/15fe19c8a3235853?imageslim)

git rebase --onto 目标 `commit`   起点 `commit`  终点 `commit`

git rebase --onto 第3个commit 第4个commit branch1

![img](https://user-gold-cdn.xitu.io/2017/11/22/15fe24400d7d73d0?imageslim)

# reset 的本质

## 移动 HEAD 以及它所指向的 branch

git reset --hard branch2

![img](https://user-gold-cdn.xitu.io/2017/11/22/15fe333cb605b0de?imageslim)

## reset --hard & reset --soft & reset 不加参数

1. `--hard`：重置位置的同时，清空工作目录的所有改动；
2. `--soft`：重置位置的同时，保留工作目录和暂存区的内容，并把重置 `HEAD` 的位置所导致的新的文件差异放进暂存区。
3. `--mixed`（默认）：重置位置的同时，保留工作目录的内容，并清空暂存区。

# checkout 的本质

git checkout branch2

![checkout](https://user-gold-cdn.xitu.io/2017/11/30/160089d53b4f65a5?imageslim)

`git checkout branch名` 的本质，其实是把 `HEAD` 指向指定的 `branch`，然后签出这个 `branch` 所对应的 `commit` 的工作目录



## checkout 和 reset 的不同

`checkout` 和 `reset` 都可以切换 `HEAD` 的位置，它们除了有许多细节的差异外，最大的区别在于：`reset` 在移动 `HEAD` 时会带着它所指向的 `branch` 一起移动，而 `checkout` 不会



## stash：临时存放工作目录的改动

git stash

git stash pop

git stash -u





## reflog

![image-20181211225900641](/Users/xuhaoliang/Library/Application Support/typora-user-images/image-20181211225900641.png)