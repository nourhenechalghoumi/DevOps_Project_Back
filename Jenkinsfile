pipeline {
    agent any

    tools {
        nodejs 'NodeInstaller'
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('ss')
    }

    stages {
        stage('Checkout GIT') {
            steps {
                echo "Getting Project from Git"
                checkout scm
            }
        }

        stage('Run Unit Tests JUNIT') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Build and Test Backend') {
            steps {
                script {
                    try {
                        sh 'mvn clean install -DskipTests'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        error("Build failed: ${e.message}")
                    }
                }
            }
            post {
                success {
                    script {
                        def subject = "Notification success"
                        def body = "BUILD DONE "
                        def to = 'test.devops697@gmail.com'

                        mail(
                            subject: subject,
                            body: body,
                            to: to,
                        )
                    }
                }
                failure {
                    script {
                        def subject = "Build Failure - ${currentBuild.fullDisplayName}"
                        def body = "The build has failed in the Jenkins pipeline. Please investigate and take appropriate action."
                        def to = 'test.devops697@gmail.com'

                        mail(
                            subject: subject,
                            body: body,
                            to: to,
                        )
                    }
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('DevOps_Project_Front') {
                    script {
                        sh 'npm install'
                        sh 'ng build '
                    }
                }
            }
        }

        stage('SonarQube analysis') {
            steps {
                script {
                    withSonarQubeEnv(installationName: 'Devops') {
                        sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar -Dsonar.login=squ_3ea4356b9bfaf618b8528e69537987a92e1119a6'
                    }
                }
            }
        }

        stage('Login Docker') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        
        stage('Build & Push Docker Image (Backend)') {
            steps {
                script {
                    def imageName = "nourhenechalghoumi/devops_project"
                    sh "docker build -t $imageName ."
                    sh "docker login -u $DOCKERHUB_CREDENTIALS_USR -p $DOCKERHUB_CREDENTIALS_PSW"
                    sh "docker push $imageName"
                }
            }
        }
    
        // stage('Build Docker Image (Frontnd)') {
        //     steps {
        //         def imageName = "nourhenechalghoumi/devops_project"
        //         script {
        //             sh "docker build -t $imageName ."
        //             sh "docker push $imageName"
        //         }
        //     }
        // }

        // stage('Deploy Front/Back/DB') {
        //     steps {
        //         script {
        //             sh 'docker-compose -f docker-compose.yml up -d'
        //         }
        //     }
        // }
    }
}

