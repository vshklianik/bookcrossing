pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                powershell """echo 'Hello World and JENKINS!!!'
                    echo 'Multiline shell steps works too'"""
            }
        }
    }
}