package br.org.pti.api.nonfunctional.authengine;

import br.org.pti.api.nonfunctional.authengine.application.security.custom.model.CompositeToken;
import lombok.Data;
import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Set;

import static br.org.pti.api.nonfunctional.authengine.domain.services.ServiceToken.extractClientsId;

//@SpringBootTest
class AuthorizationServerApplicationTests {

    @Test
    void contextLoads() {

        final Set<SimpleGrantedAuthority> authorities = Set.of(
                new SimpleGrantedAuthority("root"),
                new SimpleGrantedAuthority("root/account-manager/access-groups/put/activate"),
                new SimpleGrantedAuthority("root/account-manager"),
                new SimpleGrantedAuthority("root/financial"),
                new SimpleGrantedAuthority("root/financial/insert-coin"),
                new SimpleGrantedAuthority("root/falcatrua"),
                new SimpleGrantedAuthority("root/falcatrua/outra")
        );

        Assert.isTrue("account-manager;financial;falcatrua".equals(String.join(";", extractClientsId(authorities))), "");

    }

    /**
     *
     */
    @Test
    void recursiveTokensHanlder() {

        //Creates two composites containing the tokens
        final CompositeToken compositeToken1 = new CompositeToken("compositeToken1");
        final CompositeToken compositeToken2 = new CompositeToken("compositeToken2");
        compositeToken1.add(compositeToken2);

        final CompositeToken compositeToken3 = new CompositeToken("compositeToken3");
        compositeToken1.add(compositeToken3);

        final CompositeToken compositeToken4 = new CompositeToken("compositeToken4");
        compositeToken3.add(compositeToken4);
        final CompositeToken compositeToken6 = new CompositeToken("compositeToken6");
        compositeToken4.add(compositeToken6);
        final CompositeToken compositeToken7 = new CompositeToken("compositeToken7");
        compositeToken4.add(compositeToken7);


        final CompositeToken compositeToken5 = new CompositeToken("compositeToken5");
        compositeToken3.add(compositeToken5);
        final CompositeToken compositeToken8 = new CompositeToken("compositeToken8");
        compositeToken5.add(compositeToken8);
        final CompositeToken compositeToken9 = new CompositeToken("compositeToken9");
        compositeToken5.add(compositeToken9);

        Assert.isTrue(!compositeToken1.isRevoked());
        Assert.isTrue(!compositeToken2.isRevoked());
        Assert.isTrue(!compositeToken3.isRevoked());
        Assert.isTrue(!compositeToken4.isRevoked());
        Assert.isTrue(!compositeToken6.isRevoked());
        Assert.isTrue(!compositeToken7.isRevoked());
        Assert.isTrue(!compositeToken5.isRevoked());
        Assert.isTrue(!compositeToken8.isRevoked());
        Assert.isTrue(!compositeToken9.isRevoked());

        compositeToken9.revoke();

        Assert.isTrue(compositeToken1.isRevoked());
        Assert.isTrue(compositeToken2.isRevoked());
        Assert.isTrue(compositeToken3.isRevoked());
        Assert.isTrue(compositeToken4.isRevoked());
        Assert.isTrue(compositeToken6.isRevoked());
        Assert.isTrue(compositeToken7.isRevoked());
        Assert.isTrue(compositeToken5.isRevoked());
        Assert.isTrue(compositeToken8.isRevoked());
        Assert.isTrue(compositeToken9.isRevoked());

    }

}
