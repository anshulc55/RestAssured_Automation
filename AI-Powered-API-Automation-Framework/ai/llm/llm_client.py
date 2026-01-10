from openai import OpenAI
import os

class LLMClient:
	
	def __init__(self):
		self.client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))
		
	def generate(self, system_prompt: str, user_prompt: str) -> str:
		response = self.client.chat.completions.create(
			model="gpt-5.2",
			messages=[
				{"role": "system", "content": system_prompt},
				{"role": "user", "content": user_prompt}
				]
		)
		
		print(response.choices[0].message.content)
		return response.choices[0].message.content
		