/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2016                                           
 *                                                                                                                                 
 *  Creation Date: Aug 24, 2016                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.common.rest;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.oscm.common.interfaces.security.SecurityToken;

/**
 * Implementation for the security token.
 * 
 * @author miethaner
 */
public class Token implements SecurityToken {

    private Long userId;
    private Long organizationId;
    private Long tenantId;
    private Set<String> roles = new HashSet<>();

    public Token(Long userId, Long organizationId, Long tenantId,
            Set<String> roles) {
        this.userId = userId;
        this.organizationId = organizationId;
        this.tenantId = tenantId;
        this.roles = roles;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public Long getOrganizationId() {
        return organizationId;
    }

    @Override
    public Long getTenantId() {
        return tenantId;
    }

    @Override
    public Set<String> getRoles() {
        if (roles != null) {
            return Collections.unmodifiableSet(roles);
        } else {
            return Collections.emptySet();
        }
    }
}
