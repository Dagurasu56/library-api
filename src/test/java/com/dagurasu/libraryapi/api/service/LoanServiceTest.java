package com.dagurasu.libraryapi.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dagurasu.libraryapi.api.model.entity.Book;
import com.dagurasu.libraryapi.api.model.entity.Loan;
import com.dagurasu.libraryapi.api.model.repository.LoanRepository;
import com.dagurasu.libraryapi.api.service.imp.LoanServiceImpl;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class LoanServiceTest {

	LoanService service;

	@MockBean
	LoanRepository repository;
	
	@BeforeEach
	public void init() {
		this.service = new LoanServiceImpl(repository);
	}

	@Test
	@DisplayName("Deve salvar um empréstimo.")
	public void saveLoanTest() {

		Book book = Book.builder().id(1l).build();
		String customer = "Fulano";

		Loan savingLoan = Loan.builder().book(book).customer(customer).loanDate(LocalDate.now()).build();

		Loan savedLoan = Loan.builder().id(1l).book(book).customer(customer).loanDate(LocalDate.now()).build();

		when(repository.save(savingLoan)).thenReturn(savedLoan);

		Loan loan = service.save(savingLoan);
		
		assertThat(loan.getId()).isEqualTo(savedLoan.getId());
		assertThat(loan.getBook()).isEqualTo(savedLoan.getBook());
		assertThat(loan.getCustomer()).isEqualTo(savedLoan.getCustomer());
		assertThat(loan.getLoanDate()).isEqualTo(savedLoan.getLoanDate());
		
	}

}