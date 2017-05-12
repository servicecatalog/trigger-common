/*******************************************************************************
 *                                                                              
 *  Copyright FUJITSU LIMITED 2016                                           
 *                                                                                                                                 
 *  Creation Date: Jun 24, 2016                                                      
 *                                                                              
 *******************************************************************************/

package org.oscm.common.interfaces.exceptions;

import org.oscm.common.interfaces.config.MessageKey;

/**
 * Component exception for not found events and errors (e.g. not found object)
 * 
 * @author miethaner
 */
public class NotFoundException extends ServiceException {

    private static final long serialVersionUID = -8581467028129479875L;

    /**
     * Creates new not found exception
     * 
     * @param messageKey
     *            the enum key for the message
     * @param values
     *            the values for message placeholders
     */
    public NotFoundException(MessageKey messageKey, String... values) {
        super(messageKey, values);
    }

    /**
     * Creates new not found exception
     * 
     * @param messageKey
     *            the enum key for the message
     * @param e
     *            the causing exception.
     * @param values
     *            the values for message placeholders
     */
    public NotFoundException(MessageKey messageKey, Throwable e,
            String... values) {
        super(messageKey, e, values);
    }
}
