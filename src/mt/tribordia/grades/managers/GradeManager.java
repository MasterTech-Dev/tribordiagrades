package mt.tribordia.grades.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Team;

import mt.tribordia.grades.Main;
import mt.tribordia.grades.grades.Grade;

public class GradeManager {
	protected final Main main;
	private Map<String, Grade> grades;
	
	public GradeManager(Main main) {
		this.main = main;
		this.grades = new HashMap<>();
	}
	
	public Grade create(String name) {
		String prefix = "&7";
		String suffix = "";
		String separator = "&7&l>";
		String position = "";
		boolean defaultState = false;
		List<String> permissions = new ArrayList<>();
		
		Grade grade = new Grade(name, prefix, suffix, separator, position, defaultState, permissions);
		
		this.main.getGradesConfig().set(name + ".prefix", prefix);
		this.main.getGradesConfig().set(name + ".suffix", suffix);
		this.main.getGradesConfig().set(name + ".separator", separator);
		this.main.getGradesConfig().set(name + ".position", position);
		this.main.getGradesConfig().set(name + ".default", defaultState);
		this.main.getGradesConfig().set(name + ".permissions", permissions);
		
		this.main.saveGradesConfig();
		
		return grade;
	}
	public Grade load(String name) {
		String prefix = this.main.getGradesConfig().getString(name + ".prefix");
		String suffix = this.main.getGradesConfig().getString(name + ".suffix");
		String separator = this.main.getGradesConfig().getString(name + ".separator");
		String position = this.main.getGradesConfig().getString(name + ".position");
		boolean defaultState = this.main.getGradesConfig().getBoolean(name + ".default");
		List<String> permissions = this.main.getGradesConfig().getStringList(name + ".permissions");
		
		Grade grade = new Grade(name, prefix, suffix, separator, position, defaultState, permissions);
		
		if (this.isReady(name)) {			
			Team team = this.main.getTabOrder().registerNewTeam(getFinalPosition(position));
			team.setPrefix(prefix.replace("&", "§"));
			team.setSuffix(suffix.replace("&", "§"));
			this.grades.put(name, grade);
		}
		
		return grade;
	}
	public void delete(String name) {
		File file = this.main.getGradesFile();
		this.grades.remove(name);
		file.delete();
		
		new BukkitRunnable() {
			@Override
			public void run() {
				try {
					file.createNewFile();
					
					for (Grade grade : grades.values()) save(grade);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.runTaskLater(this.main, 5l);
	}
	public void save(Grade grade) {
		String name = grade.getName();
		String prefix = grade.getPrefix();
		String suffix = grade.getSuffix();
		String separator = grade.getSeparator();
		String position = grade.getPosition();
		boolean defaultState = grade.isDefault();
		List<String> permissions = grade.getPermissions();
		
		this.main.getGradesConfig().set(name + ".prefix", prefix);
		this.main.getGradesConfig().set(name + ".suffix", suffix);
		this.main.getGradesConfig().set(name + ".separator", separator);
		this.main.getGradesConfig().set(name + ".position", position);
		this.main.getGradesConfig().set(name + ".default", defaultState);
		this.main.getGradesConfig().set(name + ".permissions", permissions);
		
		this.main.saveGradesConfig();
	}
	
	public boolean isCreated(String name) {
		return this.main.getGradesConfig().contains(name);
	}
	public boolean isLoaded(String name) {
		return this.grades.containsKey(name);
	}
	public boolean isReady(String name) {
		return !this.main.getGradesConfig().getString(name + ".position").equals("");
	}
	
	public String getFinalPosition(String position) {
		String finalPosition = "";
		int i = 0;
		while (i < (5 - position.length())) {
			i++;
			finalPosition += "0";
		}
		finalPosition += position;
		return finalPosition;
	}
	public Grade get(String name) {
		return this.grades.get(name);
	}
	public Grade getDefault() {
		for (Grade grade : this.grades.values()) if (grade.isDefault()) return grade;
		return null;
	}
}
