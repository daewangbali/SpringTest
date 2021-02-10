package kr.co.roottest;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Configuration
@Data
//AllArgsConstructor 생성자 주입
@RequiredArgsConstructor // final인 애들 찾아서 주입
public class Swordman {
	//@Autowired
	
	//@Setter{onMethod = @Autowired} setter 주입s
	final Weapon weapon;

}
