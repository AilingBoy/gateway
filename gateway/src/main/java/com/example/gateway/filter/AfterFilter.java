package com.example.gateway.filter;

import com.example.gateway.utils.RsaUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 代志豪
 * 2021/12/13 17:57
 */
//@Component
public class AfterFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

//        System.out.println(System.currentTimeMillis() + "前置");
//        ServerHttpRequest request = exchange.getRequest();
//        MultiValueMap<String, String> queryParams = request.getQueryParams();
//        String s = queryParams.getFirst("s");
//        if (!StringUtils.isEmpty(s)) {
//            try {
//                String decrypt = RsaUtil.decrypt(s, RsaUtil.getPrivateKey());
//                List<String> list = new ArrayList<>();
//                list.add(decrypt);
//                queryParams.put("s",list);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            System.out.println(System.currentTimeMillis());
            System.out.println("后置1");
            exchange.getResponse().getHeaders().add("publicKey", RsaUtil.getPublicKey());
            exchange.getResponse().getHeaders().add("Access-Control-Expose-Headers", "publicKey");
        }));
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
