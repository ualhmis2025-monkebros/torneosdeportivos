pipeline {
  agent any

  tools {
    maven "maven default"  // Asegúrate de que así se llama en Global Tool Configuration
  }

  stages {
    stage('Git fetch') {
      steps {
        git branch: 'main', url: 'https://github.com/ualhmis2025-monkebros/torneosdeportivos.git'
      }
    }

    stage('Compile, Test, Package') {
      steps {
        sh "mvn -f pom.xml clean package"
      }
      post {
        success {
          junit '**/target/surefire-reports/TEST-*.xml'
          archiveArtifacts '**/target/*.jar'
          jacoco(
            execPattern: '**/target/jacoco.exec',
            classPattern: '**/target/classes',
            sourcePattern: '**/src/',
            exclusionPattern: '**/test/'
          )
          publishCoverage adapters: [jacocoAdapter('**/target/site/jacoco/jacoco.xml')]
        }
      }
    }

    stage ('Analysis') {
      steps {
        sh "mvn -f pom.xml site"
      }
      post {
        success {
          dependencyCheckPublisher pattern: '**/target/site/dependency-check-report.xml'
          recordIssues enabledForFailure: true, tool: checkStyle()
          recordIssues enabledForFailure: true, tool: pmdParser()
          recordIssues enabledForFailure: true, tool: cpd()
          recordIssues enabledForFailure: true, tool: findBugs()
          recordIssues enabledForFailure: true, tool: spotBugs()
        }
      }
    }

    stage ('Documentation') {
      steps {
        sh "mvn -f pom.xml javadoc:javadoc javadoc:aggregate"
      }
      post {
        success {
          step([$class: 'JavadocArchiver', javadocDir: 'target/site/apidocs', keepAll: false])
          publishHTML(target: [
            reportName: 'Maven Site',
            reportDir: 'target/site',
            reportFiles: 'index.html',
            keepAll: false
          ])
        }
      }
    }
  }
}

