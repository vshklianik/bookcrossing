pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                powershell '''echo "Hello World!!!"
                    echo "Multiline shell steps works too"
                    ls -lah
                '''
            }
        }
    }
}