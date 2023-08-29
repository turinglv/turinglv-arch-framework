### This is a personalized framework designed to address issues such as development efficiency, with the aim of achieving plug-and-play functionality.

### If you want to push your code to a private Maven repository, you can use the following command to modify the package name and groupId.

```shell
// projectGroup set new groupId
// projectVersion set ner version
// newPackageName set class new package name
gradle clean :cat-spring-boot-starter:publish -PprojectGroup=com.xxx -PprojectVersion=1.0.0-SNAPSHOT -PnewPackageName=com.xxx                                                                                                 ─╯
```

