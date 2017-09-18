package com.vanke.journal.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vanke.journal.entity.Journal;
import com.vanke.journal.enums.Tech;
import com.vanke.journal.enums.Type;
import com.vanke.journal.service.JournalService;
import com.vanke.journal.utils.TimeUtil;
import com.vanke.journal.web.common.ResponseData;
import com.vanke.journal.web.pojo.EventInput;
import com.vanke.journal.web.pojo.EventOuput;

@Controller
public class JournalController extends BaseController {

	@Autowired
	private JournalService journalServcie;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public ResponseData hello(HttpServletRequest request) {
		logger.info(request.getRemoteAddr());
		return new ResponseData(0, "hello", "success");
	}

	@RequestMapping(value = "/journal", method = RequestMethod.GET)
	public String journal(Model model) {
		String date_now = TimeUtil.toDateString(now);
		model.addAttribute("now", date_now);
		return "journal";
	}
	
	@RequestMapping(value = "/journal/{eventId}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable int eventId) {
		Journal journal = journalServcie.findOne(eventId);
		model.addAttribute("journal", journal);
		return "detail";
	}

	@RequestMapping(value = "/journal/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData add(EventInput input) throws Exception {
		Journal journal = new Journal();
		journal.setContent(input.getContent());
		journal.setCreateTime(now);
		journal.setEndTime(TimeUtil.toDate(input.getTime() + " 23:59:59"));
		journal.setStartTime(TimeUtil.toDate(input.getTime() + " 00:59:59"));
		journal.setProject(input.getProject());
		journal.setRemarks(input.getRemarks());
		journal.setTech(Tech.getTech(input.getTech()));
		journal.setTitle(input.getTitle());
		journal.setType(Type.getType(input.getType()));
		journalServcie.save(journal);
		ResponseData res = new ResponseData(0, journal, "成功");
		return res;
	}

	@RequestMapping(value = "/loadEvent", method = RequestMethod.GET)
	@ResponseBody
	public List<EventOuput> loadEvent(Model model, @RequestParam(value = "start", required = true) String start,
			@RequestParam(value = "end", required = true) String end) throws Exception {
		logger.info("start:::" + start);
		logger.info("end:::" + end);
		Date startTime = TimeUtil.toDate(start + " 00:00:00");
		Date endTime = TimeUtil.toDate(end + " 00:00:00");
		List<Journal> journals = journalServcie.findByStartTimeGreaterThanAndEndTimeLessThanOrderByStartTime(startTime,
				endTime);

		List<EventOuput> events = new ArrayList<EventOuput>();
		for (Journal j : journals) {
			EventOuput e = new EventOuput(j.getId(), j.getTitle(), TimeUtil.toDateString(j.getStartTime()),
					host + "journal/" + j.getId());
			events.add(e);
		}
		logger.info("size:::" + events.size());
		return events;
	}

}
