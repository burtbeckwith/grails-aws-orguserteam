import ikakara.orguserteam.dao.dynamo.IdOrg
import ikakara.orguserteam.dao.dynamo.IdTeam
import ikakara.orguserteam.dao.dynamo.IdUser
import ikakara.simplemarshaller.web.app.SimpleMarshallerService

class AwsOrguserteamGrailsPlugin {
  def version = "0.1"
  def grailsVersion = "2.2 > *"
  def loadAfter = ['aws-instance']
  def pluginExcludes = [
    "web-app/**"
  ]
  def title = "AWS Org-User-Group Plugin"
  def author = "Allen Arakaki"
  def description = 'Uses AWS DynamoDB to store relationships between Orgs, Users and Teams.'
  def documentation = "http://grails.org/plugin/aws-orguserteam"
  def license = "APACHE"
  def issueManagement = [url: 'https://github.com/ikakara-team/grails-aws-orguserteam/issues']
  def scm = [url: 'https://github.com/ikakara-team/grails-aws-orguserteam']

  def doWithApplicationContext = { appCtx ->
    println "Configuring AwsOrgUserTeam config ...$application.mergedConfig.conf.grails.plugin.awsorguserteam"

    if(appCtx) {
      //def sessionFactory = appCtx?.sessionFactory
      // do something here with session factory
      println 'Registering simpleMarshallerService ...'

      def simpleMarshallerService = appCtx.getBean(SimpleMarshallerService)

      // We need to register the object marshaller
      [IdOrg, IdUser, IdTeam].each { simpleMarshallerService.register it }

      println '... finished registering simpleMarshallerService'
    }

    println '... finished configuring AwsOrgUserTeam config'
  }

  def afterConfigMerge = {config, ctx ->
    // let's put the mergedConfig in ctx
    ctx.appConfig.grails.plugin.awsorguserteam.putAll(config.grails.plugin.awsorguserteam)
  }
}
