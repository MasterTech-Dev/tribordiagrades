package mt.tribordia.grades.grades;

import java.util.List;

public class Grade {
	private String name,
									prefix, // Grade's prefix
									suffix, // Grade's suffix
									separator, // Grade's chat separator
									position; // Grade's tab position
	private boolean defaultState; // Is default
	private List<String> permissions; // Grade's permissions
	
	public Grade(String name, String prefix, String suffix, String separator, String position, boolean defaultState, List<String> permissions) {
		this.name = name;
		this.prefix = prefix;
		this.suffix = suffix;
		this.separator = separator;
		this.position = position;
		this.defaultState = defaultState;
		this.permissions = permissions;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public void setSeparator(String separator) {
		this.separator = separator;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void addPermission(String permission) {
		if (hasPermission(permission)) return;
		this.permissions.add(permission);
	}
	public void removePermission(String permission) {
		if (!hasPermission(permission)) return;
		this.permissions.remove(permission);
	}
	
	public boolean hasPermission(String permission) {
		return this.permissions.contains(permission);
	}
	
	public String getName() {
		return this.name;
	}
	public String getPrefix() {
		return this.prefix;
	}
	public String getColouredPrefix() {
		return this.prefix.replace("&", "§") + (this.prefix.endsWith(" ") ? "" : " ");
	}
	public String getSuffix() {
		return this.suffix;
	}
	public String getColouredSuffix() {
		return (this.prefix.startsWith(" ") ? "" : " ") + this.suffix.replace("&", "§");
	}
	public String getSeparator() {
		return this.separator;
	}
	public String getColouredSeparator() {
		return (this.separator.startsWith(" ") ? "" : " ") + this.separator.replace("&", "§") + (this.separator.endsWith(" ") ? "" : " ");
	}
	public String getPosition() {
		return this.position;
	}
	public boolean isDefault() {
		return this.defaultState;
	}
	public List<String> getPermissions() {
		return this.permissions;
	}
}
