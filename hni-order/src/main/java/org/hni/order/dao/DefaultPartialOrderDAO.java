package org.hni.order.dao;

import org.hni.common.dao.AbstractDAO;
import org.hni.order.om.PartialOrder;
import org.springframework.stereotype.Component;

/**
 * The DAO for PartialOrders
 */
@Component
public class DefaultPartialOrderDAO extends AbstractDAO {
    public DefaultPartialOrderDAO() {
        super(PartialOrder.class);
    }
}
