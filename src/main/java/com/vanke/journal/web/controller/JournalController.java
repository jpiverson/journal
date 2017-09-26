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
import com.vanke.journal.exception.WebException;
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

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String journal(Model model) {
		String date_now = TimeUtil.toDateString(now);
		model.addAttribute("now", date_now);
		return "journal";
	}

	@RequestMapping(value = "/detail/{eventId}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable int eventId) {
		Journal journal = journalServcie.findOne(eventId);
		model.addAttribute("journal", journal);
		return "detail";
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData del(int id) throws WebException {
		Journal journal = journalServcie.findOne(id);
		if (journal == null) {
			throw new WebException("999999", "日志ID不存在");
		}
		journalServcie.delete(id);
		ResponseData res = new ResponseData(0, journal, "成功");
		return res;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseData add(EventInput input) throws WebException {
		Journal journal = null;
		boolean isAdd = true; // 添加/编辑的标志位
		if (input != null && input.getId() != null && input.getId() != 0) { // 编辑
			isAdd = false;
			journal = journalServcie.findOne(input.getId());
			if (journal == null) {
				throw new WebException("999999", "日志ID不存在");
			}
		} else {
			journal = new Journal();
		}
		
		journal.setTitle(input.getTitle());
		journal.setProject(input.getProject());
		journal.setContent(input.getContent());
		journal.setRemarks(input.getRemarks());
		journal.setTech(Tech.getTech(input.getTech()));
		journal.setType(Type.getType(input.getType()));
		if (isAdd) { // 修改日志时不需要修改这些属性
			journal.setCreateTime(now);
			try {
				journal.setEndTime(TimeUtil.toDate(input.getTime() + " 23:59:59"));
				journal.setStartTime(TimeUtil.toDate(input.getTime() + " 00:59:59"));
			} catch (Exception e) {
				throw new WebException(e, "100001", "日志ID不存在");
			}
		}
		journalServcie.save(journal);
		ResponseData res = new ResponseData(0, journal, "成功");
		return res;
	}

	@RequestMapping(value = "/loadEvent", method = RequestMethod.GET)
	@ResponseBody
	public List<EventOuput> loadEvent(Model model, @RequestParam(value = "start", required = true) String start,
			@RequestParam(value = "end", required = true) String end) throws WebException {
		try {
			Date startTime = TimeUtil.toDate(start + " 00:00:00");
			Date endTime = TimeUtil.toDate(end + " 00:00:00");
			List<Journal> journals = journalServcie
					.findByStartTimeGreaterThanAndEndTimeLessThanOrderByStartTime(startTime, endTime);
			List<EventOuput> events = new ArrayList<EventOuput>();
			for (Journal j : journals) {
				EventOuput e = new EventOuput(j.getId(), j.getTitle(), TimeUtil.toDateString(j.getStartTime()),
						host + "detail/" + j.getId(), "");
				if (j.getType() == Type.PA) {
					e.setColor("#FFB5A1");
				} else if (j.getType() == Type.RELEASE) {
					e.setColor("#5FD9CD");
				} else if (j.getType() == Type.EXPECTED_RELEASE) {
					e.setColor("#EACF02");
				}
				events.add(e);
			}
			return events;
		} catch (Exception e) {
			throw new WebException(e, "999999", "系统错误");
		}

	}

}
