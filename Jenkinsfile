node {
   stage('SCM Checkout'){

git: 'https://github.com/murari99732/MyFirstProjectOnGit'
   }
   
 stage('Mvn Package'){
	
	   
	   sh "${mvn} clean package deploy"
   }
   
   }
