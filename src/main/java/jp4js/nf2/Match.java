package jp4js.nf2;

import java.util.List;
import java.util.LinkedList;

import jp4js.nf2.tpl.DBody;
import jp4js.nf2.tpl.ListTemplate;
import jp4js.nf2.tpl.DHeader;

import jp4js.nf2.tpl.AtomicTemplate;
import jp4js.nf2.tpl.AtomicValue;
import jp4js.nf2.tpl.Template;
import jp4js.nf2.tpl.ListTuple;
import jp4js.nf2.tpl.Tuple;
import jp4js.utils.Utils;


public class Match {
    private DHeader template;
    private List<DBody> documentSet;
    private boolean valid;

    public Match(DHeader template, DBody documentSet) {
        this.template = template;
        this.documentSet = new LinkedList<>() {{add(documentSet);}};
        this.valid = this.tryMatch(this.template, this.documentSet);
    }

    public Match(DHeader template, List<DBody> documentSet) {
        this.template = template;
        this.documentSet = documentSet;
        this.valid = this.tryMatch(template, documentSet);
    }

    public boolean isValid() {
        return this.valid;
    }

    public DHeader header() {
        return this.template;
    }

    public List<DBody> body() {
        return this.documentSet;
    }

    private boolean tryMatch(DHeader header, List<DBody> documentSet) {
        Utils.isTrue(documentSet != null, "documentset must not be null");
        if (documentSet.size() == 0) {
            return true;
        }
        if (header == null) {
            return false;
        }
        for (DBody item: documentSet) {
            if (!tryMatch(header, item)) {
                return false;
            }
        }
        
        return true;
    }

    private boolean tryMatch(DHeader header, DBody document) {
        if (header instanceof ListTemplate) {
            if (!matchList((ListTemplate)header, document)) {
                return false;
            }
        }

        if (header instanceof Template) {
            if (!matchComplex((Template)header, document)) {
                return false;
            }
        }

        if (header instanceof AtomicTemplate) {
            if (!matchAtomic((AtomicTemplate)header, document)) {
                return false;
            }
        }
        return true;

    }

    private boolean matchList(ListTemplate tpl, DBody documentSet) {
        if (!(documentSet instanceof ListTuple)) {
            return false;
        }
        ListTuple data = (ListTuple)documentSet;
        for (DBody item: data) {
            if (!tryMatch(tpl.getHeader(), item)) {
                return false;
            }
        }
        return true;
    }


    private boolean matchAtomic(AtomicTemplate template, DBody documentSet) {
        if (!(documentSet instanceof AtomicValue)) {
            return false;
        }
        return true;
    }

    private boolean matchComplex(Template template, DBody documentSet) {
        if (!(documentSet instanceof Tuple)) {
            return false;
        }

        Tuple t = (Tuple)documentSet;
        if (template.size() != t.size()) {
            return false;
        }

        for (String fieldname: template) {
            if (!(tryMatch(template.get(fieldname), t.get(template.index(fieldname))))) {
                return false;
            }
        }
        return true;
    }
}