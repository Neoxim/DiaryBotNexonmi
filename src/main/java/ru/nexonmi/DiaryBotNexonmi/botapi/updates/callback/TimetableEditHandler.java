package ru.nexonmi.DiaryBotNexonmi.botapi.updates.callback;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.nexonmi.DiaryBotNexonmi.botapi.service.MessageService;
import ru.nexonmi.DiaryBotNexonmi.botapi.updates.service.MyInlineKeyboardButton;
import ru.nexonmi.DiaryBotNexonmi.data.repository.DataRepository;

public class TimetableEditHandler extends InputCallbackHandler {

    public TimetableEditHandler(MessageService messageService, DataRepository repository) {
        super(messageService, repository);
    }

    @Override
    protected BotApiMethod<?> handleCallback(CallbackQuery callback) {
        try {

            //main idea is just to set new buttons in message
            return messageService.getEditMessage(
                    callback.getMessage().getChatId(),
                    callback.getMessage().getMessageId(),
                    callback.getMessage().getText(),
                    messageService.getReplayKeyboardInMessage(makeKeyboard())
            );

        } catch (Exception e) {
            return messageService.getReplyMessage(callback.getMessage().getChatId(), "replay.some_error");
        }
    }

    private MyInlineKeyboardButton[][] makeKeyboard() {
        //TODO make next step of lesson deletion
        return new MyInlineKeyboardButton[][]{
                {
                        new MyInlineKeyboardButton(messageService.getSourceText(CallbackButtonEnum.ADD_LESSON_TO_TIMETABLE_CHOOSE_DAY.title),
                                messageService.getSourceText(CallbackButtonEnum.ADD_LESSON_TO_TIMETABLE_CHOOSE_DAY.callbackAction.commandCode))
                },
                {
                        new MyInlineKeyboardButton(messageService.getSourceText(CallbackButtonEnum.DELETE_LESSON_CHOOSE_DAY.title),
                                messageService.getSourceText(CallbackButtonEnum.DELETE_LESSON_CHOOSE_DAY.callbackAction.commandCode))
                }
        };
    }

}
