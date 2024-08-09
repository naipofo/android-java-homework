export class ProjectService {
  _projects = [];
  async load() {
    this._projects = (
      await (await fetch("http://localhost:3000/list")).json()
    ).data;
  }
  async getList() {
    this.load();
    return this._projects;
  }
  async getProject(id: any): Promise<any> {
    return (await (await fetch("http://localhost:3000/get/" + id)).json()).data;
  }
  async saveNew(
    name: string,
    tags: string,
    date: string,
    content: string
  ): Promise<any> {
    await (
      await fetch("http://localhost:3000/add", {
        method: "POST",
        body: JSON.stringify({ name, tags, date, content }),
        headers: {
          "accept": "*/*",
          "content-type": "application/json",
          "sec-fetch-mode": "cors",
        },
      })
    ).json();
  }

  async edit(
    rowid: Number,
    name: string,
    tags: string,
    date: string,
    content: string
  ): Promise<any> {
    await (
      await fetch("http://localhost:3000/edit/"+rowid, {
        method: "POST",
        body: JSON.stringify({ name, tags, date, content }),
        headers: {
          "accept": "*/*",
          "content-type": "application/json",
          "sec-fetch-mode": "cors",
        },
      })
    ).json();
  }

  async delete(
    rowid: Number,
  ): Promise<any> {
    await (
      await fetch("http://localhost:3000/delete/"+rowid, {
        headers: {
          "accept": "*/*",
          "sec-fetch-mode": "cors",
        },
      })
    ).json();
  }
}
