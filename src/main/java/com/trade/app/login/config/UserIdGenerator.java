package com.trade.app.login.config;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.annotation.Configuration;

import jakarta.persistence.*;
import java.io.Serializable;

@Configuration
public class UserIdGenerator implements IdentifierGenerator {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public Serializable generate(
            SharedSessionContractImplementor session, Object object) {

        String prefix = "U";

        // Get next sequence value
        Long nextVal = ((Number) session
                .createNativeQuery("SELECT nextval('user_seq')")
                .getSingleResult()).longValue();

        // Format → U001, U002, U003...
        return prefix + String.format("%04d", nextVal);
    }
}