package school.service.impl;

import school.mapper.OrdersMapper;
import school.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersMapper ordersMapper;
}