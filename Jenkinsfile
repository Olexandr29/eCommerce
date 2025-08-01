pipeline {
    agent any

    tools {
        maven 'Maven 3.9.6'
        jdk 'JDK 21'
        allure 'Allure 2.33.0'
        }

    stages {

        stage('Build and Test') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                bat 'mvn allure:report'
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure includeProperties: true,
                        jdk: '',
                        results: [[path:'target/allure-results']]
            }
        }

    }
}