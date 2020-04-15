package mt.tribordia.grades.accounts;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import mt.tribordia.grades.Main;
import mt.tribordia.grades.grades.Grade;

@SuppressWarnings("deprecation")
public class Account {
	private UUID owner;
	private Grade grade;
	private String lang;
	
	public Account(UUID owner, Grade grade, String lang) {
		this.owner = owner;
		this.grade = grade;
		this.lang = lang;
	}
	
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public void setPermissions() {
		Player player = Bukkit.getPlayer(owner);
		PermissionAttachment attach = player.addAttachment(Main.INSTANCE);
		for (String perm : this.grade.getPermissions()) attach.setPermission(perm, true);
		Main.INSTANCE.attachments.put(owner, attach);
	}
	public void removePermissions() {
		Player player = Bukkit.getPlayer(owner);
		player.removeAttachment(Main.INSTANCE.attachments.get(owner));
	}
	public void setNameTag() {
		Player player = Bukkit.getPlayer(owner);
		Main.INSTANCE.getTabOrder().getTeam(Main.INSTANCE.getGradeManager().getFinalPosition(this.grade.getPosition())).addPlayer(player);
		player.setDisplayName(this.getNameTag());
		for (Player p : Bukkit.getOnlinePlayers()) p.setScoreboard(Main.INSTANCE.getTabOrder());
	}
	
	public UUID getOwner() {
		return this.owner;
	}
	public Grade getGrade() {
		return this.grade;
	}
	public String getLang() {
		return this.lang;
	}
	public String getNameTag() {
		return (this.grade.getColouredPrefix() + Bukkit.getPlayer(owner).getName() + this.grade.getColouredSuffix()).replace("&", "§");
	}
}
