package drawer.utils;

import com.main.rexx.R;
import android.content.Context;

public class Utils {

	//Set all the navigation icons and always to set "zero 0" for the item is a category
	public static int[] iconNavigation = new int[] {R.drawable.ic_action_find_partner,
		R.drawable.ic_action_chat, R.drawable.dog_footprint, R.drawable.ic_action_person,
		R.drawable.ic_action_settings, R.drawable.ic_action_share };

	
	//get title of the item navigation
	public static String getTitleItem(Context context, int posicao){		
		String[] titulos = context.getResources().getStringArray(R.array.nav_menu_items);  
		return titulos[posicao];
	} 
	
}
