
to test from httpie
```bash
http GET http://localhost:8099/hello OAuth:something
```

### What Spring Security offers by default?

Secure HTTP Headers
If you compare the HTTP headers before and after adding the Spring Security, you’ll see that the framework has added some new headers:

```
Cache-Control:no-cache, no-store, max-age=0, must-revalidate
Expires:0
Pragma:no-cache
X-Content-Type-Options:nosniff
X-Frame-Options:DENY
X-XSS-Protection:1; mode=block
Let’s see what Spring Security brings to the party:
```

The Cache-Control, Expires and Pragma headers disable the caching
The X-Content-Type-Options header disables content sniffing for the browser and provides protection from the MIME type confusion attack
The X-Frame-Options header prevents clickjacking attack
The X-XSS-Protection header protects the user from the cross-site scripting attack

### Ref

[Spring_security_doc](https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/)

[open_id](https://openid.net/specs/openid-connect-core-1_0.html#UserInfo)

[Spring_security_samples](https://github.com/spring-projects/spring-security/tree/5.1.5.RELEASE/samples)

[OAuth0](https://auth0.com/docs/quickstart/backend/java-spring-security/01-authorization)


### Dynamic multi build project 

```groovy
//settings.gradle - in root project.
FileTree buildFiles = fileTree(rootDir) {
	List excludes = gradle.startParameter.projectProperties.get("excludeProjects")?.split(",")
	include '**/*.gradle'
	exclude 'build', '**/gradle', 'settings.gradle', 'buildSrc', '/build.gradle', '.*', 'out'
	exclude '**/grails3'
	if(excludes) {
		exclude excludes
	}
}

String rootDirPath = rootDir.absolutePath + File.separator
buildFiles.each { File buildFile ->

	boolean isDefaultName = 'build.gradle'.equals(buildFile.name)
	if(isDefaultName) {
		String buildFilePath = buildFile.parentFile.absolutePath
		String projectPath = buildFilePath.replace(rootDirPath, '').replace(File.separator, ':')
		include projectPath
	} else {
		String projectName = buildFile.name.replace('.gradle', '');
		String projectPath = ':' + projectName;
		include projectPath
		def project = findProject("${projectPath}")
		project.name = projectName
		project.projectDir = buildFile.parentFile
		project.buildFileName = buildFile.name
	}
}
```

```groovy
def webProjects() {
    subprojects.findAll { subproject -> subproject.plugins.hasPlugin('war') }
}

gradle.projectsEvaluated {
    configure(webProjects()) {
        ...
    }
}
```
