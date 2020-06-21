node {
	 def mvn = tool (name: 'maven3', type: 'maven') + '/bin/mvn'
   stage('SCM Checkout'){

git: 'https://github.com/murari99732/MyFirstProjectOnGit'
   }
   
 stage("Compile-Package"){
	
	   
	   sh "mvn package"
   }
   
   }
