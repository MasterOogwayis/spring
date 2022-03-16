# Transactional Advisor #


```text



TransactionAutoConfiguration -> @EnableTransactionManagement -> TransactionManagementConfigurationSelector -> 
ProxyTransactionManagementConfiguration -> BeanFactoryTransactionAttributeSourceAdvisor -> 
(TransactionInterceptor & TransactionAttributeSource(AnnotationTransactionAttributeSource)) -> TransactionAttributeSourcePointcut -> 
TransactionAttributeSourceClassFilter -> BeanFactoryTransactionAttributeSourceAdvisor.transactionAttributeSource


BeanFactoryTransactionAttributeSourceAdvisor

TransactionAttributeSourceAdvisor


TransactionAttributeSourcePointcut(transactionInterceptor) -> TransactionAttributeSourceClassFilter

TransactionAttributeSourceClassFilter -> transactionInterceptor.getTransactionAttributeSource().





```