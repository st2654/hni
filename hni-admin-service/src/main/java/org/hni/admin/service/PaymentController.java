package org.hni.admin.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hni.common.exception.HNIException;
import org.hni.order.om.Order;
import org.hni.payment.om.PaymentInstrument;
import org.hni.payment.service.PaymentInstrumentService;
import org.hni.provider.om.Provider;
import org.hni.provider.service.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@Api(value = "/payments")
@SwaggerDefinition( info = @Info(
		description = "provides methods of payment and associating thsoe payments with an order/user",
		version = "v1",
		title = "Payments API"
))
@Component
@Path("/payments")
public class PaymentController extends AbstractBaseController {
	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Inject private ProviderService providerService;
	@Inject private PaymentInstrumentService paymentInstrumentService;
	
	@GET
	@Path("/payment-instruments/")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Returns the order with the given id"
		, notes = ""
		, response = Order.class
		, responseContainer = "")
	public Collection<PaymentInstrument> getPaymentInstrumentsForProvider(@QueryParam("providerId") Long id, @QueryParam("amount") Double amount) {
		Provider provider = providerService.get(id);
		if ( null != provider ) {
			return paymentInstrumentService.with(provider, amount);
		}
		throw new HNIException("The provider specified is not valid");
	}

}
