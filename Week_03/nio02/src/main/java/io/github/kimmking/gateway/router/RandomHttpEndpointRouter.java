package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.Random;

public class RandomHttpEndpointRouter implements HttpEndpointRouter {

    @Override
    public String route(List<String> endpoints) {
        int randomIndex = new Random().nextInt(endpoints.size());
        return endpoints.get(randomIndex);
    }
}
