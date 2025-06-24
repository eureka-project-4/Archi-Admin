package com.archiadmin.user.service.impl;

import com.archiadmin.user.entity.Contract;
import com.archiadmin.user.entity.User;
import com.archiadmin.user.repository.ContractRepository;
import com.archiadmin.user.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;

    @Override
    public void renewContract(LocalDate today) {
        List<Contract> expiredContractList = contractRepository.findByEndDate(today)
                .orElse(new ArrayList<>());

        for(Contract contract : expiredContractList) {
            User user = contract.getUser();

            Contract renewedContract = Contract.builder()
                    .productBundle(contract.getProductBundle())
                    .user(user)
                    .paymentMethod(contract.getPaymentMethod())
                    .price(contract.getPrice())
                    .startDate(today.atStartOfDay().plusDays(1))
                    .endDate((today.atStartOfDay().plusMonths(1)))
                    .build();

            contractRepository.save(renewedContract);
        }

    }
}
