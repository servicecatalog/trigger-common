/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2016                                           
 *                                                                                                                                 
 *  Creation Date: Aug 25, 2016                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.common.rest.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.oscm.common.rest.Token;

/**
 * Unit test for Token
 * 
 * @author miethaner
 */
public class TokenTest {

    @Test
    public void testFields() {
        
        Set<String> roles = new TreeSet<>();
        roles.add("test");
        Token test = new Token(new Long(1), new Long(2), new Long(3), roles);

        assertEquals(new Long(1), test.getUserId());
        assertEquals(new Long(2), test.getOrganizationId());
        assertEquals(new Long(3), test.getTenantId());
        assertTrue(test.getRoles().contains("test"));

        test = new Token(null, null, null, null);

        assertNull(test.getUserId());
        assertNull(test.getOrganizationId());
        assertNull(test.getTenantId());
        assertTrue(test.getRoles().isEmpty());
    }
}
