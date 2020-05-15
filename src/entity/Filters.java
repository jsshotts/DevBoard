package entity;

public class Filters {
	
	public enum Language {
		PYTHON("Python"),
		JAVA("Java"),
		C("C"),
		CPP("C++"),
		JAVASCRIPT("JavaScript"),
		SWIFT("Swift"),
		KOTLIN("Kotlin");	
		
		private String language;
		
		Language(String lang) {
			this.language = lang;
		}
		
		public String getString() {
			return this.language;
		}
	}
	
	public enum ProjectPlatform {
		IOS("iOS"),
		ANDROID("Android"),
		WINDOWS("Windows"),
		LINUX("Linux"),
		MAC("Mac");
		
		private String projectPlatform;
		
		ProjectPlatform(String plat){
			this.projectPlatform = plat;
		}
		
		public String getString() {
			return projectPlatform;
		}
	}
}