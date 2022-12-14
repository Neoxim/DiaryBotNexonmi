package ru.nexonmi.DiaryBotNexonmi.botapi.updates.message;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.nexonmi.DiaryBotNexonmi.botapi.updates.callback.CallbackButtonEnum;
import ru.nexonmi.DiaryBotNexonmi.botapi.updates.service.GetUserInterface;
import ru.nexonmi.DiaryBotNexonmi.botapi.service.MessageService;
import ru.nexonmi.DiaryBotNexonmi.botapi.updates.service.MyInlineKeyboardButton;
import ru.nexonmi.DiaryBotNexonmi.data.repository.DataRepository;
import ru.nexonmi.DiaryBotNexonmi.domain.entity.DayEntity;
import ru.nexonmi.DiaryBotNexonmi.domain.entity.UserEntity;

class TimetableEditHandler extends InputMessageHandler implements GetUserInterface {


    public TimetableEditHandler(MessageService messageService, DataRepository repository) {
        super(messageService, repository);
    }

    @Override
    public UserEntity get(long chat_id) {
        return repository.get(chat_id);
    }

    @Override
    protected SendMessage handleMessage(Message message) {
        try {
            UserEntity user = get(message.getChatId());
            StringBuilder ansStrBuilder = new StringBuilder();
            ansStrBuilder.append(messageService.getSourceText(MessageEnum.TIMETABLE_EDIT.replayCode));
            for (DayEntity day : user.getDiary().getDays()) {
                ansStrBuilder.append(messageService.getRussianStringDay(day.getDayOfWeek().getDayNum()))
                        .append(":\n");
                for (int lessonId : day.getLessonIDs())
                    ansStrBuilder.append(user.getDiary().getUserLessons().get(lessonId).getName()).append("\n");

                ansStrBuilder.append("\n");
            }

            SendMessage sendMessage = messageService.getReplyMessage(message.getChatId(), ansStrBuilder.toString());
            sendMessage.setReplyMarkup(messageService.getReplayKeyboardInMessage(makeKeyboard()));
            return sendMessage;
        } catch (Exception e) {
            return messageService.getReplyMessage(message.getChatId(), "replay.some_error");
        }
    }

    private MyInlineKeyboardButton[][] makeKeyboard() {
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
