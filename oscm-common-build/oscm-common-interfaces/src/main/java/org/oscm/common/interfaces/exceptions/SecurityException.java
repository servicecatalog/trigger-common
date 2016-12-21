/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2016                                           
 *                                                                                                                                 
 *  Creation Date: Jul 13, 2016                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.common.interfaces.exceptions;

import org.oscm.common.interfaces.config.ErrorKey;

/**
 * Component exception for security events and errors (e.g. permissions)
 * 
 * @author miethaner
 */
public class SecurityException extends ServiceException {

    private static final long serialVersionUID = 6780718172186488863L;

    /**
     * Creates new security exception
     * 
     * @param errorKey
     *            the enum key for the error
     * @param values
     *            the values for message placeholders
     */
    public SecurityException(ErrorKey errorKey, String... values) {
        super(errorKey, values);
    }

    /**
     * Creates new security exception
     * 
     * @param errorKey
     *            the enum key for the error
     * @param e
     *            the causing exception.
     * @param values
     *            the values for message placeholders
     */
    public SecurityException(ErrorKey errorKey, Throwable e, String... values) {
        super(errorKey, e, values);
    }
}
