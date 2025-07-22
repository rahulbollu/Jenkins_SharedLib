// def call(String Project, String ImageTag, String dockerhubuser){
//   withCredentials([usernamePassword(credentialsId: 'docker', passwordVariable: 'dockerhubpass', usernameVariable: 'dockerhubuser')]) {
//       sh "docker login -u ${dockerhubuser} -p ${dockerhubpass}"
//   }
//   sh "docker push ${dockerhubuser}/${Project}:${ImageTag}"
// }


def call(String Project, String ImageTag, String dockerhubuser) {
  withCredentials([usernamePassword(credentialsId: 'docker', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {

    // ✅ Safe Docker login using password via stdin
    sh 'echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin'

    // ✅ Push the Docker image
    sh "docker push ${dockerhubuser}/${Project}:${ImageTag}"
  }
}
