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
		
		private String lang;
		
		Language(String lang) {
			this.lang = lang;
		}
		
		public String getString() {
			return this.lang;
		}
	}
	
	public enum ProjectPlatform {
		IOS("iOS"),
		ANDROID("Android"),
		WINDOWS("Windows"),
		LINUX("Linux"),
		MAC("Mac");
		
		private String plat;
		
		ProjectPlatform(String plat){
			this.plat = plat;
		}
		
		public String getString() {
			return plat;
		}
	}
}