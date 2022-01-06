pipeline {
    environment {
        registry = "sample/simple-java-maven-app"
        registryCredential = 'dockerhub_id'
        dockerImage = ''
}
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {

        stage('Clone repository') {
            steps {
            /* Clone our repository */
                checkout scm
            }    
        }

        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Deliver') {
            steps {
                sh './jenkins/scripts/deliver.sh'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }
        // stage('Building image') {
        //     agent none
        //     steps{
        //         script {
        //             dockerImage = docker.build registry + ":$BUILD_NUMBER"
        //         }
        //     }
        // }
    }
}

stage('Build image') {
    node {
        def app
        /* Build the docker image */
        app = docker.build("simple-java-maven-app:$BUILD_NUMBER")
    }
}
