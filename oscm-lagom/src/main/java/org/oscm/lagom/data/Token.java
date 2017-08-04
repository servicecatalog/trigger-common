/*******************************************************************************
 *
 *  Copyright FUJITSU LIMITED 2016                                           
 *
 *  Creation Date: Aug 24, 2016                                                      
 *
 *******************************************************************************/

package org.oscm.lagom.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Entity class for the security token.
 *
 * @author miethaner
 */
public class Token {

    public static final String FIELD_USER_ID = "user_id";
    public static final String FIELD_ORGANIZATION_ID = "organization_id";
    public static final String FIELD_TENANT_ID = "tenant_id";
    public static final String FIELD_RESTRICTIONS = "restrictions";
    public static final String FIELD_ROLES = "roles";

    private UUID userId;

    private UUID organizationId;

    private UUID tenantId;

    private Set<String> restrictions;

    private Set<String> roles;

    @JsonCreator
    public Token(@JsonProperty(FIELD_USER_ID) UUID userId,
        @JsonProperty(FIELD_ORGANIZATION_ID) UUID organizationId,
        @JsonProperty(FIELD_TENANT_ID) UUID tenantId,
        @JsonProperty(FIELD_RESTRICTIONS) Set<String> restrictions,
        @JsonProperty(FIELD_ROLES) Set<String> roles) {
        this.userId = userId;
        this.organizationId = organizationId;
        this.tenantId = tenantId;
        this.restrictions = restrictions;
        this.roles = roles;
    }

    public Token(String userId, String organizationId, String tenantId,
        String[] restrictions, String[] roles) {
        if (userId != null) {
            this.userId = UUID.fromString(userId);
        }

        if (organizationId != null) {
            this.organizationId = UUID.fromString(organizationId);
        }

        if (tenantId != null) {
            this.tenantId = UUID.fromString(tenantId);
        }

        if (restrictions != null) {
            this.restrictions = new HashSet<>(Arrays.asList(restrictions));
        }

        if (roles != null) {
            this.roles = new HashSet<>(Arrays.asList(roles));
        }
    }

    /**
     * Gets the user id. Returns null if not set.
     *
     * @return the user id
     */
    @JsonProperty(FIELD_USER_ID)
    public UUID getUserId() {
        return userId;
    }

    /**
     * Gets the user id as string. Returns null if not set.
     *
     * @return the id string
     */
    public String getUserIdAsString() {
        if (userId != null) {
            return userId.toString();
        } else {
            return null;
        }
    }

    /**
     * Gets the organization id. Returns null if not set.
     *
     * @return the organization id.
     */
    @JsonProperty(FIELD_ORGANIZATION_ID)
    public UUID getOrganizationId() {
        return organizationId;
    }

    /**
     * Gets the organization id as string. Returns null if not set.
     *
     * @return the id string
     */
    public String getOrganizationIdAsString() {
        if (organizationId != null) {
            return organizationId.toString();
        } else {
            return null;
        }
    }

    /**
     * Gets the tenant id. Returns null if not set.
     *
     * @return the tenant id
     */
    @JsonProperty(FIELD_TENANT_ID)
    public UUID getTenantId() {
        return tenantId;
    }

    /**
     * Gets the tenant id as string. Returns null if not set.
     *
     * @return the id string
     */
    public String getTenantIdAsString() {
        if (tenantId != null) {
            return tenantId.toString();
        } else {
            return null;
        }
    }

    /**
     * Gets the set of restrictions. Returns null if not set.
     *
     * @return the set of restrictions
     */
    @JsonProperty(FIELD_RESTRICTIONS)
    public Set<String> getRestrictions() {
        if (restrictions != null) {
            return Collections.unmodifiableSet(restrictions);
        } else {
            return Collections.emptySet();
        }
    }

    /**
     * Gets the array of restrictions. Returns null if not set.
     *
     * @return the array of restrictions
     */
    public String[] getRestrictionsAsArray() {
        if (restrictions != null) {
            return restrictions.toArray(new String[] {});
        } else {
            return new String[] {};
        }
    }

    /**
     * Gets the set of roles. Returns null if not set.
     *
     * @return the set of roles
     */
    @JsonProperty(FIELD_ROLES)
    public Set<String> getRoles() {
        if (roles != null) {
            return Collections.unmodifiableSet(roles);
        } else {
            return Collections.emptySet();
        }
    }

    /**
     * Gets the array of roles. Returns null if not set.
     *
     * @return the array of roles
     */
    public String[] getRolesAsArray() {
        if (roles != null) {
            return roles.toArray(new String[] {});
        } else {
            return new String[] {};
        }
    }
}
