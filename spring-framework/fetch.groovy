def newVersion = "4.0.0.M2"
def oldVersion = "4.0.0.M1"

def modules = ["spring-aop","spring-aspects","spring-beans","spring-context","spring-context-support","spring-core","spring-expression","spring-instrument","spring-instrument-tomcat","spring-jdbc","spring-jms","spring-messaging","spring-orm","spring-orm-hibernate4","spring-oxm","spring-test","spring-test-mvc","spring-tx","spring-web","spring-webmvc","spring-webmvc-portlet","spring-webmvc-tiles3","spring-websocket"]


modules.each { m ->
	def newUrl = "http://repo.springsource.org/libs-staging-local/org/springframework/$m/$newVersion/$m-${newVersion}.pom"
	def oldUrl = "http://repo.springsource.org/milestone/org/springframework/$m/$oldVersion/$m-${oldVersion}.pom"

	def newPom = new File("build/$newVersion/${m}"+".pom")
	def oldPom = new File("build/$oldVersion/${m}"+".pom")
	if (!newPom.exists()) {
		newPom.parentFile.mkdirs()
		new URL(newUrl).withInputStream{ i -> newPom.withOutputStream{ it << i }}
	}
	if (!oldPom.exists()) {
		oldPom.parentFile.mkdirs()
		try {
			new URL(oldUrl).withInputStream{ i -> oldPom.withOutputStream{ it << i }}
		}catch(FileNotFoundException notFound) { println "Could not find $oldUrl" + notFound.message }
	}
}
