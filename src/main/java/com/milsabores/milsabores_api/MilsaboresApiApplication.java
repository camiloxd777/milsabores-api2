package com.milsabores.milsabores_api;

import com.milsabores.milsabores_api.product.Product;
import com.milsabores.milsabores_api.repository.ProductRepository;
import com.milsabores.milsabores_api.user.User;
import com.milsabores.milsabores_api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MilsaboresApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MilsaboresApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(UserRepository userRepository,
								  ProductRepository productRepository,
								  PasswordEncoder passwordEncoder) {
		return args -> {

			User admin = userRepository.findByEmail("admin@milsabores.cl");

			if (admin == null) {
				// No existe -> lo creamos
				admin = User.builder()
						.email("admin@milsabores.cl")
						.nombre("Administrador")
						.username("admin")
						.fechaNacimiento("2003-07-01")
						.password(passwordEncoder.encode("admin123")) // ENCRIPTADA
						.codigoPromo(null)
						.rol("ADMIN")
						.build();
			} else {
				// Ya existe -> aseguramos rol y password encriptada
				admin.setRol("ADMIN");

				String pwd = admin.getPassword();
				// Si la password no parece BCrypt (no empieza con $2...), la re-encriptamos
				if (pwd != null &&
						!pwd.startsWith("$2a$") &&
						!pwd.startsWith("$2b$") &&
						!pwd.startsWith("$2y$")) {

					admin.setPassword(passwordEncoder.encode("admin123"));
				}
			}

			userRepository.save(admin);

			// ==========================
			// 2) PRODUCTOS SOLO SI TABLA VACÍA
			// ==========================
			if (productRepository.count() == 0L) {

				// Tortas cuadradas
				productRepository.save(
						Product.builder()
								.nombre("Torta cuadrada de chocolate")
								.descripcion("Deliciosa torta de chocolate con capas de ganache y avellanas.")
								.precio(45000)
								.categoria("Tortas Cuadradas")
								.activo(true)
								.build()
				);
				productRepository.save(
						Product.builder()
								.nombre("Torta cuadrada de frutas")
								.descripcion("Exquisita torta con frutas frescas bañada en gelatina brillante.")
								.precio(50000)
								.categoria("Tortas Cuadradas")
								.activo(true)
								.build()
				);

				// Tortas circulares
				productRepository.save(
						Product.builder()
								.nombre("Torta circular de manjar")
								.descripcion("Bizcocho de vainilla relleno con manjar y con crema chantillí.")
								.precio(40000)
								.categoria("Tortas Circulares")
								.activo(true)
								.build()
				);
				productRepository.save(
						Product.builder()
								.nombre("Torta circular de vainilla")
								.descripcion("Bizcocho de vainilla relleno con crema pastelera y un glaseado dulce.")
								.precio(42000)
								.categoria("Tortas Circulares")
								.activo(true)
								.build()
				);

				// Postres individuales
				productRepository.save(
						Product.builder()
								.nombre("Mousse de chocolate")
								.descripcion("Postre individual hecho con chocolate de alta calidad.")
								.precio(5000)
								.categoria("Postres Individuales")
								.activo(true)
								.build()
				);
				productRepository.save(
						Product.builder()
								.nombre("Tiramisú clásico")
								.descripcion("Postre italiano con capas de bizcocho de soletilla, café, crema y cacao.")
								.precio(5500)
								.categoria("Postres Individuales")
								.activo(true)
								.build()
				);

				// Productos sin azúcar
				productRepository.save(
						Product.builder()
								.nombre("Torta sin azúcar de naranja")
								.descripcion("Torta de naranja endulzada naturalmente.")
								.precio(48000)
								.categoria("Productos sin azúcar")
								.activo(true)
								.build()
				);
				productRepository.save(
						Product.builder()
								.nombre("Cheesecake sin azúcar")
								.descripcion("Cheesecake con base de nueces, endulzado con stevia.")
								.precio(47000)
								.categoria("Productos sin azúcar")
								.activo(true)
								.build()
				);

				// Pastelería tradicional
				productRepository.save(
						Product.builder()
								.nombre("Empanada de manzana")
								.descripcion("Empanada de hojaldre rellena de manzana canela.")
								.precio(3000)
								.categoria("Pastelería tradicional")
								.activo(true)
								.build()
				);
				productRepository.save(
						Product.builder()
								.nombre("Tarta de Santiago")
								.descripcion("Tarta tradicional de almendras.")
								.precio(6000)
								.categoria("Pastelería tradicional")
								.activo(true)
								.build()
				);

				// Productos sin gluten
				productRepository.save(
						Product.builder()
								.nombre("Brownie sin gluten")
								.descripcion("Brownie de chocolate, elaborado con harina de almendras.")
								.precio(4000)
								.categoria("Productos sin gluten")
								.activo(true)
								.build()
				);
				productRepository.save(
						Product.builder()
								.nombre("Pan sin gluten")
								.descripcion("Pan elaborado con harina de arroz y tapioca.")
								.precio(3500)
								.categoria("Productos sin gluten")
								.activo(true)
								.build()
				);

				// Productos veganos
				productRepository.save(
						Product.builder()
								.nombre("Torta vegana de chocolate")
								.descripcion("Torta de chocolate sin ingredientes de origen animal.")
								.precio(50000)
								.categoria("Productos veganos")
								.activo(true)
								.build()
				);
				productRepository.save(
						Product.builder()
								.nombre("Galletas veganas de avena")
								.descripcion("Galletas de avena con pasas y canela. Sin lácteos ni huevos.")
								.precio(4500)
								.categoria("Productos veganos")
								.activo(true)
								.build()
				);

				// Tortas especiales
				productRepository.save(
						Product.builder()
								.nombre("Torta especial de cumpleaños")
								.descripcion("Torta decorada para celebraciones especiales, personalizable.")
								.precio(55000)
								.categoria("Tortas especiales")
								.activo(true)
								.build()
				);
				productRepository.save(
						Product.builder()
								.nombre("Torta especial de boda")
								.descripcion("Elegante torta nupcial de varios pisos.")
								.precio(60000)
								.categoria("Tortas especiales")
								.activo(true)
								.build()
				);
			}
		};
	}
}