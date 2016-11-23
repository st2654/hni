package org.hni.admin.service;

import java.util.Collection;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hni.common.exception.HNIException;
import org.hni.order.om.Order;
import org.hni.order.om.OrderItem;
import org.hni.order.service.OrderService;
import org.hni.payment.om.OrderPayment;
import org.hni.payment.om.PaymentInfo;
import org.hni.payment.service.OrderPaymentService;
import org.hni.payment.service.PaymentInstrumentService;
import org.hni.provider.om.Menu;
import org.hni.provider.om.MenuItem;
import org.hni.provider.om.Provider;
import org.hni.provider.om.ProviderLocation;
import org.hni.provider.service.ProviderService;
import org.hni.user.om.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.Match;

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
	@Inject private OrderService orderService;
	@Inject private OrderPaymentService orderPaymentService;
	@Inject private PaymentInstrumentService paymentInstrumentService;
	
	@GET
	@Path("/payment-instruments/")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Returns a collectio of payment instruments that can be used to pay for an order"
		, notes = "encrypted"
		, response = Order.class
		, responseContainer = "")
	public String getPaymentInstrumentsForProvider(@QueryParam("orderId") Long orderId, @QueryParam("providerId") Long providerId, @QueryParam("amount") Double amount) {
		Provider provider = providerService.get(providerId);
		Order order = orderService.get(orderId);
		if ( null != order && null != provider ) {
			// TODO: encrypt!
			Collection<OrderPayment> payments = orderPaymentService.paymentFor(order, provider, amount, getLoggedInUser());
			return serializeOrderPaymentToJson(payments);
		}
		throw new HNIException("The provider specified is not valid");
	}

	@POST
	@Path("/order-payments/")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(value = "Returns a collection of payment instruments that can be used to pay for an order"
		, notes = "encrypted"
		, response = Order.class
		, responseContainer = "")
	public String setPaymentInstrumentsForOrder(Set<PaymentInfo> paymentInfos) {
		Order order = orderPaymentService.assignPayment(paymentInfos, getLoggedInUser());
		logger.info(String.format("Marking order %d complete", order.getId()));
		orderService.complete(order);
		
		return "{}";
	}
	
	private String serializeOrderPaymentToJson(Collection<OrderPayment> orderPayments) {
		try {
			String json = mapper.writeValueAsString(JsonView.with(orderPayments)
					.onClass(Order.class, Match.match().exclude("*").include("id", "subtotal"))
					.onClass(User.class, Match.match().exclude("*").include("id", "firstName", "lastName"))
			.onClass(Provider.class, Match.match().exclude("*").include("id", "name")));
			return json;
		} catch (JsonProcessingException e) {
			logger.error("Serializing User object:"+e.getMessage(), e);
		}
		return "{}";
	}
}
