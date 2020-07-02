pipeline {
    agent {
        label 'master'
    }

    parameters {
        choice(name: 'tag', choices: ['AddPlace', 'DeletePlace', 'Regression'], description: '')
    }

	options {
        timestamps ()
        disableConcurrentBuilds()
    }
    
    triggers {
		pollSCM 'H/1 * * * *'
	}

    stages {
        stage('Checkout') {
            steps {
                git credentialsId: 'aneesa_password', url: 'https://github.com/Aneesa666/RestAPI.git'
            }
        }
        stage('Build') {
            steps {
                bat label: '', script: 'mvn test verify -Dcucumber.options="--tags @"%tag%""'
            }
            post {
                always {
                    cucumber failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: 'target/jsonReports/**/*.json', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
                }
            }
        }
    }
}

