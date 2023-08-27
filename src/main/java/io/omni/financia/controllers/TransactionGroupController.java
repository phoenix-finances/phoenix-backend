package io.omni.financia.controllers;

import io.omni.financia.domains.AppUser;
import io.omni.financia.domains.TransactionGroup;
import io.omni.financia.domains.TransactionTimeline;
import io.omni.financia.dto.TransactionGroupDto;
import io.omni.financia.services.AppUserService;
import io.omni.financia.services.TransactionGroupService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction-groups")
public class TransactionGroupController {
    private @Resource TransactionGroupService transactionGroupService;
    private @Resource AppUserService appUserService;

    @GetMapping
    private List<TransactionGroupDto> getGroups(Principal principal){
        TransactionTimeline timeline = appUserService.getUserTransactionTimeline(Long.parseLong(principal.getName()));
        return transactionGroupService.getGroupsByTimelineId(timeline.getId())
                .stream()
                .map(TransactionGroup::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{ledgerId}")
    private List<TransactionGroup> getTransactionByLedger(@PathVariable long ledgerId) {
        return transactionGroupService.getGroupsByTimelineId(ledgerId);
    }

    @PostMapping
    private TransactionGroupDto createTransaction(
            @RequestBody TransactionGroupDto data,
            Principal principal){
        AppUser user = appUserService.getUser(Long.parseLong(principal.getName()));
        return transactionGroupService.addTransaction(data.toEntity(), user)
                .toDto();
    }

    @DeleteMapping("/transactionId")
    private void delete(@PathVariable long transactionId){
        transactionGroupService.deleteTransaction(transactionId);
    }
}
