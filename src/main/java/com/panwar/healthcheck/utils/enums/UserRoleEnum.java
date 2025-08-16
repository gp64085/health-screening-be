package com.panwar.healthcheck.utils.enums;

public enum UserRoleEnum  {
	USER("USER"), ADMIN("ADMIN");

	final String role;
	UserRoleEnum(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	
	/**
	 * Converts a string to its corresponding {@link UserRoleEnum} if it exists.
	 * 
	 * @param role the string to convert
	 * @return the corresponding {@link UserRoleEnum}
	 * @throws IllegalArgumentException if the string does not match any of the
	 *                                  {@link UserRoleEnum} values
	 */
	public static UserRoleEnum fromString(String role) throws IllegalArgumentException {
		for (UserRoleEnum userRole : UserRoleEnum.values()) {
			if (userRole.role.equalsIgnoreCase(role)) {
				return userRole;
			}
		}
		throw new IllegalArgumentException("No enum constant for role: " + role);
	}
}
