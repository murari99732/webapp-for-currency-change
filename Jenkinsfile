node {
	 def mvn = tool (name: 'maven3', type: 'maven') + '/bin/mvn'
   stage('SCM Checkout'){

git: 'https://github.com/murari99732/MyFirstProjectOnGit'
   }
   
 stage("Compile-Package"){
	
	   
	   sh "mvn package"
   }
   
   }
! This jenkins file will not work as Jenkins pipeline automation like checkout building testing deployment and triggering the push will not work  for localhost.
