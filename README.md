# scapi
java spring RESTFUL API SAMPLE PROJECT

###features
1. with @Cacheable annotaion for bot redis, ehcache
2. Basic http status and related result Code with standand payload
3. event, error log
4. AOP for handling resultCode and Exception Handling
5. little admin console to handle the cache


###HTTP REQUEST FLOW


        (get/post)                                  DTO                         DOMAIN
user  ------------> (AOP)------> Controller ------------------------>Service -----------> DAO
                                (return                             (return               (return dataObject)
                                ResponseEntity<ResultJson>)         ResultJson)
