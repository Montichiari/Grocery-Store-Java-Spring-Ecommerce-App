import { format } from "date-fns";

const DEFAULT_DATE_FORMAT = "dd MMM yyyy";

const dateFormat = {
  formatter(date: Date | string) {
    return format(new Date(date), DEFAULT_DATE_FORMAT);
  },
};

export default dateFormat;
