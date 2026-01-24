pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven'
    }

    environment {
        MAVEN_OPTS = "-Dmaven.repo.local=.m2/repository"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/Ambikesh-Sarangi/SeleniumAutomationFramework.git'
            }
        }

        stage('Run Tests (Headless)') {
            steps {
                sh '''
                    mvn clean test \
                    -Dsurefire.suiteXmlFiles=testng.xml
                '''
            }
        }
    }

    post {
        always {
            echo "Archiving reports & logs"

            archiveArtifacts artifacts: 'reports/**', allowEmptyArchive: true
            archiveArtifacts artifacts: 'screenshots/**', allowEmptyArchive: true
            archiveArtifacts artifacts: 'logs/**', allowEmptyArchive: true
            publishHTML([
				allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/extent-reports',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Automation Report'
			])
        }
    }
}
