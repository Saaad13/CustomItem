package fr.sad.earthskyitems.utils;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;
import java.util.*;
import java.util.Map.Entry;

public class ItemCreator {

	private ItemStack item;
	private Player possesseur;
	private String creator_name;
	private int slot;

	public ItemCreator(Material material) {
		item = new ItemStack(material);
	}

	public ItemCreator(PotionType potionType){
		item = new Potion(potionType).toItemStack(1);
	}

	public ItemCreator(ItemStack item) {
		setMaterial(item.getType());
		setAmount(item.getAmount());
		setDurability(item.getDurability());
		setName(item.getItemMeta().getDisplayName());
		setEnchantments(item.getItemMeta().getEnchants());
		setLores(item.getItemMeta().getLore());
	}

	public ItemCreator(ItemCreator itemcreator) {
		this.item = itemcreator.getItem();
		this.possesseur = itemcreator.getPossesseur();
		this.creator_name = itemcreator.getCreator_name();
	}

	public ItemStack getItem() {
		return item;
	}

	public Material getMaterial() {
		return item.getType();
	}

	public ItemCreator setUnbreakable(Boolean unbreakable) {
		ItemMeta meta = item.getItemMeta();
		meta.setUnbreakable(unbreakable);
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemCreator setMaterial(Material material) {
		if (item == null) {
			item = new ItemStack(material);
		} else {
			item.setType(material);
		}
		return this;
	}

	public Integer getAmount() {
		return item.getAmount();
	}

	public ItemCreator setAmount(Integer amount) {
		item.setAmount(amount);
		return this;
	}

	public Short getDurability() {
		return item.getDurability();
	}

	public Integer getDurabilityInteger() {
		return (int) item.getDurability();
	}

	public ItemCreator setDurability(Short durability) {
		item.setDurability(durability);
		return this;
	}

	public ItemCreator setDurability(Integer durability) {
		Short shortdurability = ((short) ((int) durability));
		item.setDurability(shortdurability);
		return this;
	}

	public ItemMeta getMeta() {
		return item.getItemMeta();
	}

	public ItemCreator setMeta(ItemMeta meta) {
		item.setItemMeta(meta);
		return this;
	}

	public String getName() {
		return item.getItemMeta().getDisplayName();
	}

	public ItemCreator setName(String name) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return this;
	}

	public ArrayList<String> getLores() {
		return (ArrayList<String>) item.getItemMeta().getLore();
	}

	public ItemCreator setLores(List<String> list) {
		ItemMeta meta = item.getItemMeta();
		List<String> lores = new ArrayList<>();
		if(meta.getLore() != null){
			lores.addAll(meta.getLore());
		}
		if(list != null){
			for(String a : list)
				lores.add(a);
		}
		meta.setLore(lores);
		item.setItemMeta(meta);
		return this;
	}

	public ItemCreator setLores(boolean text, List<String> list, List<String> list2) {
		ItemMeta meta = item.getItemMeta();
		List<String> lores = new ArrayList<>();
		if(meta.getLore() != null){
			lores.addAll(meta.getLore());
		}
		if(text){
			for(String a : list)
				lores.add(a);
		}else{
			for(String a : list2)
				lores.add(a);
		}
		meta.setLore(lores);
		item.setItemMeta(meta);
		return this;
	}

	public ItemCreator setLores(String... list) {
		ItemMeta meta = item.getItemMeta();
		List<String> lores = new ArrayList<>();
		if(meta.getLore() != null){
			lores.addAll(meta.getLore());
		}
		for(String a : list)
			if(!a.equalsIgnoreCase("null"))
				lores.add(a);

		meta.setLore(lores);
		item.setItemMeta(meta);
		return this;
	}


	public ItemCreator clearLores() {
		ItemMeta meta = item.getItemMeta();
		meta.setLore(new ArrayList<String>());
		item.setItemMeta(meta);
		return this;
	}

	public ItemCreator addLore(String lore) {
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lores = (ArrayList<String>) meta.getLore();
		if (lores == null) {
			lores = new ArrayList<>();
		}
		if (lore != null) {
			lores.add(lore);
		} else {
			lores.add(" ");
		}
		meta.setLore(lores);
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemCreator addItemFlag(ItemFlag i) {
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(i);
		item.setItemMeta(meta);
		return this;
	}
	
	public ItemCreator addallItemsflags() {
		ItemMeta meta = item.getItemMeta();
		for(ItemFlag flags : ItemFlag.values()) {
			meta.addItemFlags(flags);
		}
		item.setItemMeta(meta);
		return this;
	}
	
	public HashMap<Enchantment, Integer> getEnchantments() {
		return new HashMap<Enchantment, Integer>(item.getItemMeta().getEnchants());
	}

	public ItemCreator setEnchantments(Map<Enchantment, Integer> map) {
		ItemMeta meta = item.getItemMeta();
		if (meta.getEnchants() != null) {
			ArrayList<Enchantment> cloneenchantments = new ArrayList<>(meta.getEnchants().keySet());
			for (Enchantment enchantment : cloneenchantments) {
				meta.removeEnchant(enchantment);
			}
		}
		for (Entry<Enchantment, Integer> e : map.entrySet()) {
			meta.addEnchant(e.getKey(), e.getValue(), true);
		}
		item.setItemMeta(meta);
		return this;
	}

	public ItemCreator clearEnchantments() {
		ItemMeta meta = item.getItemMeta();
		if (meta.getEnchants() != null) {
			ArrayList<Enchantment> cloneenchantments = new ArrayList<>(meta.getEnchants().keySet());
			for (Enchantment enchantment : cloneenchantments) {
				meta.removeEnchant(enchantment);
			}
			item.setItemMeta(meta);
		}
		return this;
	}

	public ItemCreator addEnchantment(Enchantment enchantment, Integer lvl) {
		ItemMeta meta = item.getItemMeta();
		meta.addEnchant(enchantment, lvl, true);
		item.setItemMeta(meta);
		return this;
	}

	public ItemCreator removeEnchantment(Enchantment enchantment) {
		ItemMeta meta = item.getItemMeta();
		if (meta.getEnchants() != null) {
			if (meta.getEnchants().containsKey(enchantment)) {
				meta.removeEnchant(enchantment);
				item.setItemMeta(meta);
			}
		}
		return this;
	}

	public Enchantment[] getTableauEnchantments() {
		Enchantment[] enchantments = new Enchantment[] {};
		if (item.getItemMeta().getEnchants() != null) {
			Integer i = 0;
			for (Enchantment enchantment : item.getItemMeta().getEnchants().keySet()) {
				enchantments[i] = enchantment;
				i++;
			}
		}
		return enchantments;
	}

	public Integer[] getTableauEnchantmentslvl() {
		Integer[] enchantmentslvl = new Integer[] {};
		if (item.getItemMeta().getEnchants() != null) {
			Integer i = 0;
			for (Integer enchantmentlvl : item.getItemMeta().getEnchants().values()) {
				enchantmentslvl[i] = enchantmentlvl;
				i++;
			}
		}
		return enchantmentslvl;
	}

	public ItemCreator setTableauEnchantments(Enchantment[] enchantments, Integer[] enchantmentslvl) {
		ItemMeta meta = item.getItemMeta();
		if (meta.getEnchants() != null) {
			ArrayList<Enchantment> cloneenchantments = new ArrayList<>(meta.getEnchants().keySet());
			for (Enchantment enchantment : cloneenchantments) {
				meta.removeEnchant(enchantment);
			}
		}
		for (Integer i = 0; i < enchantments.length && i < enchantmentslvl.length; i++) {
			meta.addEnchant(enchantments[i], enchantmentslvl[i], true);
		}
		item.setItemMeta(meta);
		return this;
	}

	public SkullMeta getSkullMeta() {
		if (item.getType().equals(Material.SKULL_ITEM)) {
			return (SkullMeta) item.getItemMeta();
		}
		return null;
	}

	public ItemCreator setSkullMeta(SkullMeta skullmeta) {
		if (item.getType().equals(Material.SKULL_ITEM)) {
			item.setItemMeta(skullmeta);
		}
		return this;
	}

	public String getOwner() {
		if (item.getType().equals(Material.SKULL_ITEM)) {
			return ((SkullMeta) item.getItemMeta()).getOwner();
		}
		return null;
	}

	public ItemCreator setOwner(String owner) {
		if (item.getType().equals(Material.SKULL_ITEM)) {
			SkullMeta meta = (SkullMeta) item.getItemMeta();
			meta.setOwner(owner);
			item.setItemMeta(meta);
		}
		return this;
	}

	public EnchantmentStorageMeta getEnchantmentStorageMeta() {
		if (item.getType().equals(Material.ENCHANTED_BOOK)) {
			return (EnchantmentStorageMeta) item.getItemMeta();
		}
		return null;
	}

	public ItemCreator setEnchantmentStorageMeta(EnchantmentStorageMeta enchantmentstoragemeta) {
		if (item.getType().equals(Material.ENCHANTED_BOOK)) {
			item.setItemMeta(enchantmentstoragemeta);
		}
		return this;
	}

	public HashMap<Enchantment, Integer> getStoredEnchantments() {
		if (item.getType().equals(Material.ENCHANTED_BOOK)) {
			return (HashMap<Enchantment, Integer>) ((EnchantmentStorageMeta) item.getItemMeta()).getEnchants();
		}
		return null;
	}

	public ItemCreator setStoredEnchantments(HashMap<Enchantment, Integer> storedenchantments) {
		if (item.getType().equals(Material.ENCHANTED_BOOK)) {
			EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
			if (meta.getStoredEnchants() != null) {
				ArrayList<Enchantment> clonestoredenchantments = new ArrayList<>(meta.getStoredEnchants().keySet());
				for (Enchantment storedenchantment : clonestoredenchantments) {
					meta.removeStoredEnchant(storedenchantment);
				}
			}
			for (Entry<Enchantment, Integer> e : storedenchantments.entrySet()) {
				meta.addEnchant(e.getKey(), e.getValue(), true);
			}
			item.setItemMeta(meta);
		}
		return this;
	}

	public ItemCreator clearStoredEnchantments() {
		if (item.getType().equals(Material.ENCHANTED_BOOK)) {
			EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
			if (meta.getStoredEnchants() != null) {
				ArrayList<Enchantment> clonestoredenchantments = new ArrayList<>(meta.getStoredEnchants().keySet());
				for (Enchantment storedenchantment : clonestoredenchantments) {
					meta.removeStoredEnchant(storedenchantment);
				}
				item.setItemMeta(meta);
			}
		}
		return this;
	}

	public ItemCreator addStoredEnchantment(Enchantment storedenchantment, Integer lvl) {
		if (item.getType().equals(Material.ENCHANTED_BOOK)) {
			EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
			meta.addStoredEnchant(storedenchantment, lvl, true);
			item.setItemMeta(meta);
		}
		return this;
	}

	public ItemCreator removeStoredEnchantment(Enchantment enchantment) {
		if (item.getType().equals(Material.ENCHANTED_BOOK)) {
			EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
			if (meta.getStoredEnchants() != null) {
				if (meta.getStoredEnchants().containsKey(enchantment)) {
					meta.removeEnchant(enchantment);
					item.setItemMeta(meta);
				}
			}
		}
		return this;
	}

	public Enchantment[] getTableauStoredEnchantments() {
		if (item.getType().equals(Material.ENCHANTED_BOOK)) {
			EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
			Enchantment[] storedenchantments = new Enchantment[] {};
			if (meta.getStoredEnchants() != null) {
				Integer i = 0;
				for (Enchantment storedenchantment : meta.getStoredEnchants().keySet()) {
					storedenchantments[i] = storedenchantment;
					i++;
				}
			}
			return storedenchantments;
		}
		return null;
	}

	public Integer[] getTableauStoredEnchantmentslvl() {
		if (item.getType().equals(Material.ENCHANTED_BOOK)) {
			EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
			Integer[] storedenchantmentslvl = new Integer[] {};
			if (meta.getStoredEnchants() != null) {
				Integer i = 0;
				for (Integer storedenchantmentlvl : meta.getStoredEnchants().values()) {
					storedenchantmentslvl[i] = storedenchantmentlvl;
					i++;
				}
			}
			return storedenchantmentslvl;
		}
		return null;
	}

	public ItemCreator setTableauStoredEnchantments(Enchantment[] storedenchantments, Integer[] storedenchantmentslvl) {
		if (item.getType().equals(Material.ENCHANTED_BOOK)) {
			EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
			if (meta.getStoredEnchants() != null) {
				ArrayList<Enchantment> clonestoredenchantments = new ArrayList<>(meta.getStoredEnchants().keySet());
				for (Enchantment storedenchantment : clonestoredenchantments) {
					meta.removeStoredEnchant(storedenchantment);
				}
			}
			for (Integer i = 0; i < storedenchantments.length && i < storedenchantmentslvl.length; i++) {
				meta.addEnchant(storedenchantments[i], storedenchantmentslvl[i], true);
			}
			item.setItemMeta(meta);
		}
		return this;
	}

	public Player getPossesseur() {
		return possesseur;
	}

	public ItemCreator setPossesseur(Player possesseur) {
		this.possesseur = possesseur;
		return this;
	}

	public String getCreator_name() {
		return creator_name;
	}

	public ItemCreator setCreator_name(String creator_name) {
		this.creator_name = creator_name;
		return this;
	}
	public int getSlot() {
		return slot;
	}

	public ItemCreator setSlot(int slot) {
		this.slot = slot;
		return this;
	}

    public ItemCreator setArmorColor(Color color) {
		if (item.getType().name().contains("LEATHER_")) {
			LeatherArmorMeta armorMeta = (LeatherArmorMeta) item.getItemMeta();
			armorMeta.setColor(color);
			item.setItemMeta(armorMeta);
		}
		return this;
    }

    public boolean hasName() {
		return getName() != null;
    }

	public ItemCreator setGlow(Boolean glow) {
		if (glow) {
			net.minecraft.server.v1_12_R1.ItemStack minecraftitemstack = CraftItemStack.asNMSCopy(item);
			NBTTagCompound tag = null;
			if (!minecraftitemstack.hasTag()) {
				tag = new NBTTagCompound();
				minecraftitemstack.setTag(new NBTTagCompound());
			} else {
				tag = minecraftitemstack.getTag();
			}
			NBTTagList ench = new NBTTagList();
			tag.set("ench", ench);
			minecraftitemstack.setTag(tag);
			item = CraftItemStack.asCraftMirror(minecraftitemstack);
		} else {
			net.minecraft.server.v1_12_R1.ItemStack minecraftitemstack = CraftItemStack.asNMSCopy(item);
			NBTTagCompound tag = null;
			if (!minecraftitemstack.hasTag()) {
				tag = minecraftitemstack.getTag();
				if (tag.hasKey("ench")) {
					tag.remove("ench");
					minecraftitemstack.setTag(tag);
					item = CraftItemStack.asCraftMirror(minecraftitemstack);
				}
			}
		}
		return this;
	}

	/*public ItemStack toHead(String owner){
		URL url = null;
		try {
			url = new URL("https://api.mojang.com/users/profiles/minecraft/" + owner);
		} catch (MalformedURLException e) {
			try {
				url = new URL("https://api.mojang.com/users/profiles/minecraft/" + "Steve");
			} catch (MalformedURLException malformedURLException) {
				malformedURLException.printStackTrace();
			}
		}
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(url.openStream());
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		String uuid = new JsonParser().parse(reader).getAsJsonObject().get("id").getAsString();

		URL url2 = null;
		try {
			url2 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?=unsigned=false");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		InputStreamReader reader2 = null;
		try {
			reader2 = new InputStreamReader(url2.openStream());
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		JsonObject property = new JsonParser().parse(reader2).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
		String texture = property.get("value").getAsString();

		JsonParser parser = new JsonParser();
		JsonObject o = parser.parse(new String(Base64.decodeBase64(texture))).getAsJsonObject();
		String skinUrl = o.get("textures").getAsJsonObject().get("SKIN").getAsJsonObject().get("url").getAsString();
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta headMeta = getSkullMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		byte[] encodedData = Base64.encodeBase64(("{textures:{SKIN:{url:\"" + skinUrl + "\"}}}").getBytes());
		profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
		Field profileField = null;
		try {
			profileField = headMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(headMeta, profile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		head.setItemMeta(headMeta);

		return head;
	}*/
}