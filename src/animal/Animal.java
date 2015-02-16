package animal;

import java.util.ArrayList;
import java.util.Date;

import android.graphics.Bitmap;

public class Animal {

	private String id;
	private String name;
	private String family;
	private String breed;
	private String weight;
	private Bitmap animalPicture;
	private String color1;
	private String color2;
	private String color3;
	private String gender;
	private String description;
	private Date dob;
	private int pedigree;

	public Animal() {

	}

	public Animal(String id, String name, String family, String weight,
			Bitmap animalPicture, String color1, String color2, String color3,
			String gender, String description, Date dob, int pedigree) {
		this();
		this.id = id;
		this.name = name;
		this.family = family;
		this.weight = weight;
		this.animalPicture = animalPicture;
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.gender = gender;
		this.description = description;
		this.dob = dob;
		this.pedigree = pedigree;
	}

	public static boolean newAnimal(String id, String nome, String familia, String gender, String raca, String peso,
			String dob, String temPedigree, String color1, String color2, String color3,
			String descricao) {
		
		JsonAnimal.newAnimal(id, nome, familia, gender, raca, peso, dob, temPedigree, color1, color2, color3, descricao);

		return true;
	}
	
	public static ArrayList<Animal> getAnimalByFamily(String family){
		return JsonAnimal.getFamilyAnimals(family);
	}

	public static ArrayList<Animal> getAnimalByUserId(String userId){
		return JsonAnimal.getAnimalByUserId(userId);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Bitmap getAnimalPicture() {
		return animalPicture;
	}

	public void setAnimalPicture(Bitmap animalPicture) {
		this.animalPicture = animalPicture;
	}

	public String getColor1() {
		return color1;
	}

	public void setColor1(String color1) {
		this.color1 = color1;
	}

	public String getColor2() {
		return color2;
	}

	public void setColor2(String color2) {
		this.color2 = color2;
	}

	public String getColor3() {
		return color3;
	}

	public void setColor3(String color3) {
		this.color3 = color3;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getPedigree() {
		return pedigree;
	}

	public void setPedigree(int pedigree) {
		this.pedigree = pedigree;
	}

}
