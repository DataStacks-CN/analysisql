# AnalysisQL

## getTopics（获取主题）

**request**

```json
{
  "type": "getTopics"
}
```

**response**

```json
{
  "sessionId": "...",
  "code": ...,
  "msg": "...",
  "rows": [{"value": "topic1"}, {"value": "topic2"}, ...]
}
```

## getDimentions（获取维度）

**request**

```json
{
  "type": "getDimentions",
  "topic": "topic1"
}
```

**response**

```json
{
  "sessionId": "...",
  "code": ...,
  "msg": "...",
  "rows": [{"value": "dimention1"}, {"value": "dimention2"}, ...]
}
```

## getDimentionValues（获取维度值）

**request**

```json
{
  "type": "getDimentionValues",
  "topic": "topic1",
  "dimention": "dimention1",
  "where": $DIMENTION_FILTER
}
```

**response**

```json
{
  "sessionId": "...",
  "code": ...,
  "msg": "...",
  "rows": [{"value": "value1"}, {"value": "value2"}, ...]
}
```

## getMetrics（获取指标）

**request**

```json
{
  "type": "getMetrics",
  "topic": "topic1"
}
```

**response**

```json
{
  "sessionId": "...",
  "code": ...,
  "msg": "...",
  "rows": [{"value": "metric1"}, {"value": "metric2"}, ...]
}
```

## query（查询）

**request**

```json
{
	"type": "query",
	"topic": "topic1",
	"interval": {"start": "$START_TIME", "end": "$END_TIME"},
	"granularity": {"data": n, "unit": "s/m/h/d/w/M/q/y"},
	"metric": "metric1",
	"where": $DIMENTION_FILTER,
	"groups": ["dimention1", "dimention2", "..."],
	"having": $METRIC_FILTER,
	"orders": [{"name": "dimention1", "sort": "asc"}, {"name": "metric1", "sort": "desc"}, ...],
	"limit": 1000
}
```

**response**

```json
{
  "sessionId": "...",
  "code": ...,
  "msg": "...",
  "rows": [{"dimention1": $STRING, "metric1": $NUMBER, "...": ...}, ...]
}
```

### $START_TIME/$END_TIME

```text
  yyyy-MM-dd HH:mm:ss
```

### $DIMENTION_FILTER/$METRIC_FILTER

```json
{
	"operator": "and/or",
	"filters": [$FILTER1, $FILTER2, ...]
}

{
	"operator": "not",
	"filter": $FILTER
}

{
	"operator": "eq/ne/gt/lt/ge/le",
	"name": "$DIMENTION/$METRIC",
	"value": $STRING/$NUMBER
}

{
	"operator": "in",
	"name": "$DIMENTION/$METRIC",
	"values": [$STRING/$NUMBER, ...]
}

{
	"operator": "regex",
	"name": "$DIMENTION",
	"pattern": "$JAVA_REGULAR_EXPRESSION"
}
```
