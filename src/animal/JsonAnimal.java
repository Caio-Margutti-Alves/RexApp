package animal;

import http.HttpUtil;

import java.util.ArrayList;
import java.util.Date;


import org.json.JSONArray;
import org.json.JSONObject;

import user.User;

public class JsonAnimal {

	private static final String TAG_ANIMAL = "animal";
	//private static final String TAG_USER_ANIMALS = "userAnimals";
	
	private static final String TAG_USER_BY = "usuario.";
	private static final String TAG_USER_ID = "id";
	
	private static final String TAG_ANIMAL_ID = "id";
	private static final String TAG_NAME = "nome";
	private static final String TAG_FAMILY = "familia";
	private static final String TAG_GENDER = "gender";
	private static final String TAG_BREED = "raca";
	private static final String TAG_WEIGHT = "peso";
	private static final String TAG_DOB = "nascimento";
	private static final String TAG_PEDIGREE = "temPedigree";
	private static final String TAG_COLOR1 = "color1";
	private static final String TAG_COLOR2 = "color2";
	private static final String TAG_COLOR3 = "color3";
	private static final String TAG_DESCRIPTION = "descricao";

	// private static final String TAG_ANIMAL_PICTURE = "animalPicture";

	public static ArrayList<Animal> getFamilyAnimals(String family) {
		HttpUtil client = new HttpUtil();

		// MEXER AQUI
		client.AddParam(TAG_FAMILY, family);

		try {
			client.Execute(HttpUtil.RequestMethod.POST, HttpUtil.getUrlGetFamilyAnimals());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return parseJsonAnimal(client.getResponse());
	}
	
	public static ArrayList<Animal> getAnimalByUserId(String userId) {
		HttpUtil client = new HttpUtil();

		// MEXER AQUI
		client.AddParam(TAG_USER_ID, userId);

		//client.AddHeader(TAG_USER_ANIMALS, "2");

		try {
			client.Execute(HttpUtil.RequestMethod.POST, HttpUtil.getUrlGetAnimalByUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return parseJsonAnimal(client.getResponse());
	}

	public static ArrayList<Animal> parseJsonAnimal(String in) {
		ArrayList<Animal> animals = null;
		
		try {
			JSONObject reader = new JSONObject(in);
		    JSONArray jsonAnimals = reader.getJSONArray(TAG_ANIMAL);       
            animals = new ArrayList<Animal>();
            
            // looping through All Contacts
            for (int i = 0; i < jsonAnimals.length(); i++) {
                JSONObject jsonAnimal = jsonAnimals.getJSONObject(i);
			
				Animal animal = new Animal();

				animal.setId(jsonAnimal.getString(TAG_ANIMAL_ID));
				animal.setName(jsonAnimal.getString(TAG_NAME));
				animal.setFamily(jsonAnimal.getString(TAG_FAMILY));
				animal.setGender(jsonAnimal.getString(TAG_GENDER));
				animal.setBreed(jsonAnimal.getString(TAG_BREED));
				animal.setWeight(jsonAnimal.getString(TAG_WEIGHT));
				animal.setDob(new Date(jsonAnimal.getString(TAG_DOB)));
				animal.setPedigree(Integer.parseInt(jsonAnimal.getString(TAG_PEDIGREE)));
				animal.setColor1(jsonAnimal.getString(TAG_COLOR1));
				animal.setColor2(jsonAnimal.getString(TAG_COLOR2));
				animal.setColor3(jsonAnimal.getString(TAG_COLOR3));
				
				animals.add(animal);
            }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return animals;
	}

	public static void newAnimal(String id, String nome, String familia, String gender, String raca, String peso,
			String dob, String temPedigree, String color1, String color2, String color3,
			String descricao){

		HttpUtil client = new HttpUtil();

		// MEXER AQUI
		client.AddParam(TAG_ANIMAL_ID, id);
		client.AddParam(TAG_NAME, nome);
		client.AddParam(TAG_GENDER, gender);
		client.AddParam(TAG_FAMILY, familia);
		client.AddParam(TAG_BREED, raca);
		client.AddParam(TAG_WEIGHT, peso);
		client.AddParam(TAG_DOB, dob);
		client.AddParam(TAG_PEDIGREE, temPedigree);
		client.AddParam(TAG_COLOR1, color1);
		client.AddParam(TAG_COLOR2, color2);
		client.AddParam(TAG_COLOR3, color3);
		client.AddParam(TAG_DESCRIPTION, descricao);

		// private static final String TAG_ANIMAL_PICTURE = "anim
		//client.AddHeader(TAG_ANIMAL, "2");

		try {
			client.Execute(HttpUtil.RequestMethod.POST, HttpUtil.getUrlNewAnimal());
		} catch (Exception e) {
			e.printStackTrace();
		}

		client.getResponse();
	}
}
